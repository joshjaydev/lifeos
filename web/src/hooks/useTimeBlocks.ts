import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { supabase } from '../lib/supabase'
import type { TimeBlock } from '../types'

export function useTimeBlocks(date?: string) {
  return useQuery({
    queryKey: ['time_blocks', date],
    queryFn: async () => {
      let query = supabase
        .from('time_blocks')
        .select('*, actions(*)')
        .order('start_time', { ascending: true })

      if (date) {
        query = query.eq('block_date', date)
      }

      const { data, error } = await query
      if (error) throw error
      return data as (TimeBlock & { actions: { title: string } })[]
    }
  })
}

interface CreateTimeBlockInput {
  action_id: string
  block_date: string
  start_time: string
  end_time: string
}

export function useCreateTimeBlock() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (input: CreateTimeBlockInput) => {
      const { data: { user } } = await supabase.auth.getUser()
      if (!user) throw new Error('Not authenticated')

      const { data, error } = await supabase
        .from('time_blocks')
        .insert({ ...input, user_id: user.id })
        .select()
        .single()

      if (error) throw error
      return data as TimeBlock
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['time_blocks'] })
    }
  })
}

export function useDeleteTimeBlock() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (id: string) => {
      const { error } = await supabase
        .from('time_blocks')
        .delete()
        .eq('id', id)

      if (error) throw error
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['time_blocks'] })
    }
  })
}
