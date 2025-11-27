import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { supabase } from '../lib/supabase'
import type { Note } from '../types'

export function useNotes(notebookId?: string) {
  return useQuery({
    queryKey: ['notes', notebookId],
    queryFn: async () => {
      let query = supabase
        .from('notes')
        .select('*')
        .order('created_at', { ascending: false })

      if (notebookId) {
        query = query.eq('notebook_id', notebookId)
      }

      const { data, error } = await query
      if (error) throw error
      return data as Note[]
    },
    enabled: !!notebookId
  })
}

export function useNote(id: string) {
  return useQuery({
    queryKey: ['note', id],
    queryFn: async () => {
      const { data, error } = await supabase
        .from('notes')
        .select('*')
        .eq('id', id)
        .single()

      if (error) throw error
      return data as Note
    },
    enabled: !!id
  })
}

export function useJournalNote(notebookId: string, date: string) {
  return useQuery({
    queryKey: ['journal', notebookId, date],
    queryFn: async () => {
      const { data, error } = await supabase
        .from('notes')
        .select('*')
        .eq('notebook_id', notebookId)
        .eq('is_journal', true)
        .eq('journal_date', date)
        .single()

      if (error && error.code !== 'PGRST116') throw error
      return data as Note | null
    },
    enabled: !!notebookId && !!date
  })
}

interface CreateNoteInput {
  notebook_id: string
  title: string
  content?: Record<string, unknown>
  is_journal?: boolean
  journal_date?: string
}

export function useCreateNote() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (input: CreateNoteInput) => {
      const { data: { user } } = await supabase.auth.getUser()
      if (!user) throw new Error('Not authenticated')

      const { data, error } = await supabase
        .from('notes')
        .insert({ ...input, user_id: user.id })
        .select()
        .single()

      if (error) throw error
      return data as Note
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['notes'] })
      queryClient.invalidateQueries({ queryKey: ['journal'] })
    }
  })
}

export function useUpdateNote() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async ({ id, ...updates }: Partial<Note> & { id: string }) => {
      const { data, error } = await supabase
        .from('notes')
        .update(updates)
        .eq('id', id)
        .select()
        .single()

      if (error) throw error
      return data as Note
    },
    onSuccess: (data) => {
      queryClient.invalidateQueries({ queryKey: ['notes'] })
      queryClient.invalidateQueries({ queryKey: ['note', data.id] })
      queryClient.invalidateQueries({ queryKey: ['journal'] })
    }
  })
}

export function useDeleteNote() {
  const queryClient = useQueryClient()

  return useMutation({
    mutationFn: async (id: string) => {
      const { error } = await supabase
        .from('notes')
        .delete()
        .eq('id', id)

      if (error) throw error
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['notes'] })
    }
  })
}
