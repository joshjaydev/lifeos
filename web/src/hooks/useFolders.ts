import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { supabase } from '../lib/supabase'
import type { Folder } from '../types'

export function useFolders() {
  return useQuery({
    queryKey: ['folders'],
    queryFn: async () => {
      const { data, error } = await supabase
        .from('folders')
        .select('*')
        .order('is_default', { ascending: false })
        .order('name')

      if (error) throw error
      return data as Folder[]
    }
  })
}

export function useCreateFolder() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (name: string) => {
      const { data: { user } } = await supabase.auth.getUser()
      if (!user) throw new Error('Not authenticated')

      const { data, error } = await supabase
        .from('folders')
        .insert({ user_id: user.id, name })
        .select()
        .single()

      if (error) throw error
      return data as Folder
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['folders'] })
    }
  })
}

export function useUpdateFolder() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async ({ id, name }: { id: string; name: string }) => {
      const { data, error } = await supabase
        .from('folders')
        .update({ name })
        .eq('id', id)
        .select()
        .single()

      if (error) throw error
      return data as Folder
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['folders'] })
    }
  })
}

export function useDeleteFolder() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (id: string) => {
      const { error } = await supabase
        .from('folders')
        .delete()
        .eq('id', id)

      if (error) throw error
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['folders'] })
      queryClient.invalidateQueries({ queryKey: ['actions'] })
    }
  })
}
