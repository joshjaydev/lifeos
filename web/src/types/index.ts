export interface Profile {
  id: string
  email: string | null
  display_name: string | null
  avatar_url: string | null
  time_block_size: number
  created_at: string
  updated_at: string
}

export interface Folder {
  id: string
  user_id: string
  name: string
  is_default: boolean
  goal_id: string | null
  created_at: string
  updated_at: string
}

export interface Action {
  id: string
  user_id: string
  folder_id: string
  title: string
  description: string | null
  due_date: string | null
  due_time: string | null
  is_recurring: boolean
  recurrence_type: 'daily' | 'weekly' | 'monthly' | 'yearly' | null
  notify_before: number
  created_at: string
  updated_at: string
}

export interface ActionCompletion {
  id: string
  action_id: string
  completed_date: string
  completed_at: string
}

export interface TimeBlock {
  id: string
  user_id: string
  action_id: string
  block_date: string
  start_time: string
  end_time: string
  created_at: string
}

export interface Event {
  id: string
  user_id: string
  title: string
  description: string | null
  start_datetime: string
  end_datetime: string
  is_recurring: boolean
  recurrence_type: 'daily' | 'weekly' | 'monthly' | 'yearly' | null
  notify_before: number
  created_at: string
  updated_at: string
}

export interface Notebook {
  id: string
  user_id: string
  name: string
  is_default: boolean
  goal_id: string | null
  created_at: string
  updated_at: string
}

export interface Note {
  id: string
  user_id: string
  notebook_id: string
  title: string
  content: Record<string, unknown>
  is_journal: boolean
  journal_date: string | null
  created_at: string
  updated_at: string
}

export interface Goal {
  id: string
  user_id: string
  what_do_you_want: string
  folder_id: string | null
  notebook_id: string | null
  created_at: string
  updated_at: string
}

export interface Principle {
  id: string
  user_id: string
  parent_id: string | null
  fundamental_truth: string
  experience: string | null
  created_at: string
  updated_at: string
}
