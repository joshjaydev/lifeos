import { useState } from 'react'
import type { Event } from '../types'

interface EventFormProps {
  event?: Event
  onSubmit: (data: EventFormData) => void
  onCancel: () => void
}

export interface EventFormData {
  title: string
  description?: string
  start_datetime: string
  end_datetime: string
  is_recurring: boolean
  recurrence_type?: 'daily' | 'weekly' | 'monthly' | 'yearly'
  notify_before: number
}

export default function EventForm({ event, onSubmit, onCancel }: EventFormProps) {
  const [formData, setFormData] = useState<EventFormData>({
    title: event?.title || '',
    description: event?.description || '',
    start_datetime: event?.start_datetime ? event.start_datetime.slice(0, 16) : '',
    end_datetime: event?.end_datetime ? event.end_datetime.slice(0, 16) : '',
    is_recurring: event?.is_recurring || false,
    recurrence_type: event?.recurrence_type || undefined,
    notify_before: event?.notify_before || 30
  })

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    onSubmit(formData)
  }

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center p-4 z-50">
      <div className="bg-bg-secondary rounded-lg p-6 w-full max-w-md">
        <h2 className="text-xl font-semibold mb-4">
          {event ? 'Edit Event' : 'New Event'}
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
            <label className="block text-sm text-gray-400 mb-1">Start</label>
            <input
              type="datetime-local"
              value={formData.start_datetime}
              onChange={(e) => setFormData({ ...formData, start_datetime: e.target.value })}
              className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
              required
            />
          </div>

          <div>
            <label className="block text-sm text-gray-400 mb-1">End</label>
            <input
              type="datetime-local"
              value={formData.end_datetime}
              onChange={(e) => setFormData({ ...formData, end_datetime: e.target.value })}
              className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
              required
            />
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
                value={formData.recurrence_type || 'daily'}
                onChange={(e) => setFormData({ ...formData, recurrence_type: e.target.value as EventFormData['recurrence_type'] })}
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
              onChange={(e) => setFormData({ ...formData, notify_before: parseInt(e.target.value) || 30 })}
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
              {event ? 'Save' : 'Create'}
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}
