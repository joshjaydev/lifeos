import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { supabase } from '../lib/supabase'
import type { Event } from '../types'

export function useEvents(startDate?: string, endDate?: string) {
  return useQuery({
    queryKey: ['events', startDate, endDate],
    queryFn: async () => {
      let query = supabase
        .from('events')
        .select('*')
        .order('start_datetime', { ascending: true })

      if (startDate) {
        query = query.gte('start_datetime', startDate)
      }
      if (endDate) {
        query = query.lte('start_datetime', endDate)
      }

      const { data, error } = await query
      if (error) throw error
      return data as Event[]
    }
  })
}

interface CreateEventInput {
  title: string
  description?: string
  start_datetime: string
  end_datetime: string
  is_recurring?: boolean
  recurrence_type?: 'daily' | 'weekly' | 'monthly' | 'yearly'
  notify_before?: number
}

export function useCreateEvent() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (input: CreateEventInput) => {
      const { data: { user } } = await supabase.auth.getUser()
      if (!user) throw new Error('Not authenticated')

      const { data, error } = await supabase
        .from('events')
        .insert({ ...input, user_id: user.id })
        .select()
        .single()

      if (error) throw error
      return data as Event
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['events'] })
    }
  })
}

export function useUpdateEvent() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async ({ id, ...updates }: Partial<Event> & { id: string }) => {
      const { data, error } = await supabase
        .from('events')
        .update(updates)
        .eq('id', id)
        .select()
        .single()

      if (error) throw error
      return data as Event
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['events'] })
    }
  })
}

export function useDeleteEvent() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (id: string) => {
      const { error } = await supabase
        .from('events')
        .delete()
        .eq('id', id)

      if (error) throw error
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['events'] })
    }
  })
}
