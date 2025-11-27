import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { supabase } from '../lib/supabase'
import type { Action } from '../types'

export function useActions(folderId?: string) {
  return useQuery({
    queryKey: ['actions', folderId],
    queryFn: async () => {
      let query = supabase
        .from('actions')
        .select('*')
        .order('created_at', { ascending: false })

      if (folderId) {
        query = query.eq('folder_id', folderId)
      }

      const { data, error } = await query
      if (error) throw error
      return data as Action[]
    }
  })
}

export function useAllActions() {
  return useQuery({
    queryKey: ['actions', 'all'],
    queryFn: async () => {
      const { data, error } = await supabase
        .from('actions')
        .select('*')
        .order('due_date', { ascending: true, nullsFirst: false })

      if (error) throw error
      return data as Action[]
    }
  })
}

interface CreateActionInput {
  folder_id: string
  title: string
  description?: string
  due_date?: string
  due_time?: string
  is_recurring?: boolean
  recurrence_type?: 'daily' | 'weekly' | 'monthly' | 'yearly'
  notify_before?: number
}

export function useCreateAction() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (input: CreateActionInput) => {
      const { data: { user } } = await supabase.auth.getUser()
      if (!user) throw new Error('Not authenticated')

      const { data, error } = await supabase
        .from('actions')
        .insert({ ...input, user_id: user.id })
        .select()
        .single()

      if (error) throw error
      return data as Action
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['actions'] })
    }
  })
}

export function useUpdateAction() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async ({ id, ...updates }: Partial<Action> & { id: string }) => {
      const { data, error } = await supabase
        .from('actions')
        .update(updates)
        .eq('id', id)
        .select()
        .single()

      if (error) throw error
      return data as Action
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['actions'] })
    }
  })
}

export function useDeleteAction() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (id: string) => {
      const { error } = await supabase
        .from('actions')
        .delete()
        .eq('id', id)

      if (error) throw error
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['actions'] })
    }
  })
}

export function useCompleteAction() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async ({ actionId, isRecurring }: { actionId: string; isRecurring: boolean }) => {
      if (isRecurring) {
        // For recurring actions, add a completion record
        const { error } = await supabase
          .from('action_completions')
          .insert({
            action_id: actionId,
            completed_date: new Date().toISOString().split('T')[0]
          })

        if (error) throw error
      } else {
        // For non-recurring actions, delete the action
        const { error } = await supabase
          .from('actions')
          .delete()
          .eq('id', actionId)

        if (error) throw error
      }
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['actions'] })
      queryClient.invalidateQueries({ queryKey: ['action_completions'] })
    }
  })
}
