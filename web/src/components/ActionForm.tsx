import { useState, useEffect } from 'react'
import type { Action, Folder } from '../types'

interface ActionFormProps {
  folders: Folder[]
  action?: Action
  defaultFolderId?: string
  onSubmit: (data: ActionFormData) => void
  onCancel: () => void
}

export interface ActionFormData {
  folder_id: string
  title: string
  description?: string
  due_date?: string
  due_time?: string
  is_recurring: boolean
  recurrence_type?: 'daily' | 'weekly' | 'monthly' | 'yearly'
  notify_before: number
}

export default function ActionForm({ folders, action, defaultFolderId, onSubmit, onCancel }: ActionFormProps) {
  const [formData, setFormData] = useState<ActionFormData>({
    folder_id: action?.folder_id || defaultFolderId || folders[0]?.id || '',
    title: action?.title || '',
    description: action?.description || '',
    due_date: action?.due_date ? action.due_date.split('T')[0] : '',
    due_time: action?.due_time || '',
    is_recurring: action?.is_recurring || false,
    recurrence_type: action?.recurrence_type || undefined,
    notify_before: action?.notify_before || 60
  })

  useEffect(() => {
    if (!formData.folder_id && folders.length > 0) {
      setFormData(prev => ({ ...prev, folder_id: defaultFolderId || folders[0].id }))
    }
  }, [folders, defaultFolderId, formData.folder_id])

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    onSubmit(formData)
  }

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center p-4 z-50">
      <div className="bg-bg-secondary rounded-lg p-6 w-full max-w-md">
        <h2 className="text-xl font-semibold mb-4">
          {action ? 'Edit Action' : 'New Action'}
        </h2>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm text-gray-400 mb-1">Title</label>
            <input
              type="text"
              value={formData.title}
              onChange={(e) => setFormData({ ...formData, title: e.target.value })}
              className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
              required
            />
          </div>

          <div>
            <label className="block text-sm text-gray-400 mb-1">Description</label>
            <textarea
              value={formData.description}
              onChange={(e) => setFormData({ ...formData, description: e.target.value })}
              className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
              rows={3}
            />
          </div>

          <div>
            <label className="block text-sm text-gray-400 mb-1">Folder</label>
            <select
              value={formData.folder_id}
              onChange={(e) => setFormData({ ...formData, folder_id: e.target.value })}
              className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
            >
              {folders.map((folder) => (
                <option key={folder.id} value={folder.id}>
                  {folder.name}
                </option>
              ))}
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-sm text-gray-400 mb-1">Due Date</label>
              <input
                type="date"
                value={formData.due_date}
                onChange={(e) => setFormData({ ...formData, due_date: e.target.value })}
                className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
              />
            </div>
            <div>
              <label className="block text-sm text-gray-400 mb-1">Due Time</label>
              <input
                type="time"
                value={formData.due_time}
                onChange={(e) => setFormData({ ...formData, due_time: e.target.value })}
                className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
              />
            </div>
          </div>

          <div>
            <label className="flex items-center gap-2">
              <input
                type="checkbox"
                checked={formData.is_recurring}
                onChange={(e) => setFormData({ ...formData, is_recurring: e.target.checked })}
                className="rounded"
              />
              <span className="text-sm">Recurring</span>
            </label>
          </div>

          {formData.is_recurring && (
            <div>
              <label className="block text-sm text-gray-400 mb-1">Repeat</label>
              <select
                value={formData.recurrence_type || ''}
                onChange={(e) => setFormData({ ...formData, recurrence_type: e.target.value as ActionFormData['recurrence_type'] })}
                className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
              >
                <option value="daily">Daily</option>
                <option value="weekly">Weekly</option>
                <option value="monthly">Monthly</option>
                <option value="yearly">Yearly</option>
              </select>
            </div>
          )}

          <div>
            <label className="block text-sm text-gray-400 mb-1">Notify before (minutes)</label>
            <input
              type="number"
              value={formData.notify_before}
              onChange={(e) => setFormData({ ...formData, notify_before: parseInt(e.target.value) || 60 })}
              className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
              min={0}
            />
          </div>

          <div className="flex gap-2 pt-4">
            <button
              type="button"
              onClick={onCancel}
              className="flex-1 bg-bg-tertiary text-gray-300 py-2 rounded hover:bg-gray-600"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="flex-1 bg-primary text-black font-semibold py-2 rounded hover:bg-primary/90"
            >
              {action ? 'Save' : 'Create'}
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}
