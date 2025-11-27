import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { supabase } from '../lib/supabase'
import type { Notebook } from '../types'

export function useNotebooks() {
  return useQuery({
    queryKey: ['notebooks'],
    queryFn: async () => {
      const { data, error } = await supabase
        .from('notebooks')
        .select('*')
        .order('is_default', { ascending: false })
        .order('name')

      if (error) throw error
      return data as Notebook[]
    }
  })
}

export function useCreateNotebook() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (name: string) => {
      const { data: { user } } = await supabase.auth.getUser()
      if (!user) throw new Error('Not authenticated')

      const { data, error } = await supabase
        .from('notebooks')
        .insert({ user_id: user.id, name })
        .select()
        .single()

      if (error) throw error
      return data as Notebook
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['notebooks'] })
    }
  })
}

export function useDeleteNotebook() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (id: string) => {
      const { error } = await supabase
        .from('notebooks')
        .delete()
        .eq('id', id)

      if (error) throw error
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['notebooks'] })
      queryClient.invalidateQueries({ queryKey: ['notes'] })
    }
  })
}
