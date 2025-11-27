import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { supabase } from '../lib/supabase'
import type { Principle } from '../types'

export function usePrinciples() {
  return useQuery({
    queryKey: ['principles'],
    queryFn: async () => {
      const { data, error } = await supabase
        .from('principles')
        .select('*')
        .order('created_at', { ascending: true })

      if (error) throw error
      return data as Principle[]
    }
  })
}

interface CreatePrincipleInput {
  fundamental_truth: string
  experience?: string
  parent_id?: string
}

export function useCreatePrinciple() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (input: CreatePrincipleInput) => {
      const { data: { user } } = await supabase.auth.getUser()
      if (!user) throw new Error('Not authenticated')

      const { data, error } = await supabase
        .from('principles')
        .insert({ ...input, user_id: user.id })
        .select()
        .single()

      if (error) throw error
      return data as Principle
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['principles'] })
    }
  })
}

export function useUpdatePrinciple() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async ({ id, ...updates }: Partial<Principle> & { id: string }) => {
      const { data, error } = await supabase
        .from('principles')
        .update(updates)
        .eq('id', id)
        .select()
        .single()

      if (error) throw error
      return data as Principle
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['principles'] })
    }
  })
}

export function useDeletePrinciple() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (id: string) => {
      const { error } = await supabase
        .from('principles')
        .delete()
        .eq('id', id)

      if (error) throw error
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['principles'] })
    }
  })
}
