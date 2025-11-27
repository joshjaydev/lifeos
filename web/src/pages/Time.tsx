import { useState } from 'react'
import { useAllActions, useCompleteAction } from '../hooks/useActions'
import { useEvents, useCreateEvent, useUpdateEvent, useDeleteEvent } from '../hooks/useEvents'
import { useTimeBlocks, useCreateTimeBlock, useDeleteTimeBlock } from '../hooks/useTimeBlocks'
import EventForm from '../components/EventForm'
import type { EventFormData } from '../components/EventForm'
import type { Action, Event } from '../types'

type Tab = 'planner' | 'block' | 'calendar'
type CalendarView = '1day' | '2day' | 'week' | 'month'

export default function Time() {
  const [activeTab, setActiveTab] = useState<Tab>('planner')

  const tabs: { id: Tab; label: string }[] = [
    { id: 'planner', label: 'Planner' },
    { id: 'block', label: 'Block' },
    { id: 'calendar', label: 'Calendar' },
  ]

  return (
    <div>
      <div className="flex gap-2 mb-6">
        {tabs.map((tab) => (
          <button
            key={tab.id}
            onClick={() => setActiveTab(tab.id)}
            className={`px-4 py-2 rounded ${
              activeTab === tab.id
                ? 'bg-primary text-black font-semibold'
                : 'bg-bg-tertiary text-gray-300 hover:bg-bg-secondary'
            }`}
          >
            {tab.label}
          </button>
        ))}
      </div>

      <div className="bg-bg-secondary rounded-lg p-6">
        {activeTab === 'planner' && <Planner />}
        {activeTab === 'block' && <TimeBlock />}
        {activeTab === 'calendar' && <Calendar />}
      </div>
    </div>
  )
}

function Planner() {
  const { data: actions = [], isLoading } = useAllActions()
  const completeAction = useCompleteAction()

  const today = new Date()
  const tomorrow = new Date(today)
  tomorrow.setDate(tomorrow.getDate() + 1)

  const formatDate = (date: Date) => date.toISOString().split('T')[0]

  const todayActions = actions.filter(a => a.due_date?.split('T')[0] === formatDate(today))
  const tomorrowActions = actions.filter(a => a.due_date?.split('T')[0] === formatDate(tomorrow))

  const handleComplete = async (action: Action) => {
    await completeAction.mutateAsync({
      actionId: action.id,
      isRecurring: action.is_recurring
    })
  }

  const renderDay = (date: Date, dayActions: Action[]) => (
    <div className="flex-1">
      <div className="text-center mb-4">
        <div className="text-3xl font-bold text-primary">{date.getDate()}</div>
        <div className="text-sm text-gray-400">
          {date.toLocaleDateString('en-US', { weekday: 'long', month: 'short' })}
        </div>
      </div>
      {dayActions.length === 0 ? (
        <p className="text-gray-500 text-sm text-center">No actions</p>
      ) : (
        <div className="space-y-2">
          {dayActions.map((action) => (
            <div key={action.id} className="bg-bg-tertiary rounded p-3 flex items-center gap-3">
              <button
                onClick={() => handleComplete(action)}
                className="w-5 h-5 rounded border-2 border-gray-500 hover:border-primary flex-shrink-0"
              />
              <div className="flex-1 min-w-0">
                <div className="font-medium truncate">{action.title}</div>
                {action.due_time && (
                  <div className="text-xs text-primary">{action.due_time}</div>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  )

  if (isLoading) return <div className="text-gray-400">Loading...</div>

  return (
    <div>
      <h2 className="text-xl font-semibold mb-4">Planner</h2>
      <div className="flex gap-8">
        {renderDay(today, todayActions)}
        {renderDay(tomorrow, tomorrowActions)}
      </div>
    </div>
  )
}

function TimeBlock() {
  const [selectedDate, setSelectedDate] = useState(new Date().toISOString().split('T')[0])
  const [blockSize, setBlockSize] = useState(10)
  const [draggedAction, setDraggedAction] = useState<Action | null>(null)

  const { data: actions = [] } = useAllActions()
  const { data: timeBlocks = [] } = useTimeBlocks(selectedDate)
  const createTimeBlock = useCreateTimeBlock()
  const deleteTimeBlock = useDeleteTimeBlock()

  // Generate time slots for the day
  const slots = []
  for (let hour = 6; hour < 22; hour++) {
    for (let min = 0; min < 60; min += blockSize) {
      slots.push({
        time: `${hour.toString().padStart(2, '0')}:${min.toString().padStart(2, '0')}`,
        label: min === 0 ? `${hour}:00` : ''
      })
    }
  }

  const handleDrop = async (startTime: string) => {
    if (!draggedAction) return

    const [hours, mins] = startTime.split(':').map(Number)
    const endMins = mins + blockSize
    const endHours = hours + Math.floor(endMins / 60)
    const endTime = `${endHours.toString().padStart(2, '0')}:${(endMins % 60).toString().padStart(2, '0')}`

    await createTimeBlock.mutateAsync({
      action_id: draggedAction.id,
      block_date: selectedDate,
      start_time: startTime,
      end_time: endTime
    })
    setDraggedAction(null)
  }

  const getBlockAtTime = (time: string) => {
    return timeBlocks.find(b => b.start_time === time + ':00' || b.start_time === time)
  }

  return (
    <div>
      <div className="flex items-center justify-between mb-4">
        <h2 className="text-xl font-semibold">Time Blocking</h2>
        <div className="flex items-center gap-4">
          <div>
            <label className="text-sm text-gray-400 mr-2">Block size:</label>
            <select
              value={blockSize}
              onChange={(e) => setBlockSize(Number(e.target.value))}
              className="bg-bg-tertiary border border-gray-600 rounded px-2 py-1 text-sm"
            >
              <option value={10}>10 min</option>
              <option value={15}>15 min</option>
              <option value={30}>30 min</option>
              <option value={60}>1 hour</option>
            </select>
          </div>
          <input
            type="date"
            value={selectedDate}
            onChange={(e) => setSelectedDate(e.target.value)}
            className="bg-bg-tertiary border border-gray-600 rounded px-2 py-1 text-sm"
          />
        </div>
      </div>

      <div className="flex gap-4 h-[500px]">
        {/* Actions list */}
        <div className="w-64 bg-bg-tertiary rounded p-3 overflow-y-auto">
          <h3 className="font-medium mb-3">Actions</h3>
          <div className="space-y-2">
            {actions.map((action) => (
              <div
                key={action.id}
                draggable
                onDragStart={() => setDraggedAction(action)}
                onDragEnd={() => setDraggedAction(null)}
                className="bg-bg-secondary rounded p-2 cursor-grab active:cursor-grabbing flex items-center gap-2"
              >
                <span className="text-gray-500">⋮⋮</span>
                <span className="truncate text-sm">{action.title}</span>
              </div>
            ))}
          </div>
        </div>

        {/* Time slots */}
        <div className="flex-1 bg-bg-tertiary rounded p-3 overflow-y-auto">
          <div className="space-y-px">
            {slots.map((slot) => {
              const block = getBlockAtTime(slot.time)
              return (
                <div
                  key={slot.time}
                  onDragOver={(e) => e.preventDefault()}
                  onDrop={() => handleDrop(slot.time)}
                  className={`flex items-center h-6 border-l-2 ${
                    slot.label ? 'border-gray-600' : 'border-transparent'
                  }`}
                >
                  <span className="w-12 text-xs text-gray-500 pr-2 text-right">
                    {slot.label}
                  </span>
                  <div className={`flex-1 h-full ${
                    block
                      ? 'bg-primary/30 border-l-2 border-primary'
                      : 'hover:bg-bg-secondary'
                  }`}>
                    {block && (
                      <div className="flex items-center justify-between px-2 h-full">
                        <span className="text-xs truncate">{block.actions?.title}</span>
                        <button
                          onClick={() => deleteTimeBlock.mutateAsync(block.id)}
                          className="text-gray-400 hover:text-red-400 text-xs"
                        >
                          ×
                        </button>
                      </div>
                    )}
                  </div>
                </div>
              )
            })}
          </div>
        </div>
      </div>
    </div>
  )
}

function Calendar() {
  const [view, setView] = useState<CalendarView>('week')
  const [currentDate, setCurrentDate] = useState(new Date())
  const [showEventForm, setShowEventForm] = useState(false)
  const [editingEvent, setEditingEvent] = useState<Event | null>(null)

  const { data: events = [], isLoading } = useEvents()
  const createEvent = useCreateEvent()
  const updateEvent = useUpdateEvent()
  const deleteEvent = useDeleteEvent()

  const handleEventSubmit = async (data: EventFormData) => {
    if (editingEvent) {
      await updateEvent.mutateAsync({ id: editingEvent.id, ...data })
    } else {
      await createEvent.mutateAsync(data)
    }
    setShowEventForm(false)
    setEditingEvent(null)
  }

  const navigateDate = (direction: number) => {
    const newDate = new Date(currentDate)
    if (view === 'month') {
      newDate.setMonth(newDate.getMonth() + direction)
    } else {
      const days = view === '1day' ? 1 : view === '2day' ? 2 : 7
      newDate.setDate(newDate.getDate() + direction * days)
    }
    setCurrentDate(newDate)
  }

  const getDaysToShow = () => {
    const days: Date[] = []
    const start = new Date(currentDate)

    if (view === 'week') {
      start.setDate(start.getDate() - start.getDay())
    }

    const count = view === '1day' ? 1 : view === '2day' ? 2 : view === 'week' ? 7 : 0

    for (let i = 0; i < count; i++) {
      const day = new Date(start)
      day.setDate(start.getDate() + i)
      days.push(day)
    }

    return days
  }

  const getEventsForDay = (date: Date) => {
    const dateStr = date.toISOString().split('T')[0]
    return events.filter(e => e.start_datetime.split('T')[0] === dateStr)
  }

  const formatTime = (datetime: string) => {
    return new Date(datetime).toLocaleTimeString('en-US', {
      hour: 'numeric',
      minute: '2-digit',
      hour12: true
    })
  }

  if (isLoading) return <div className="text-gray-400">Loading...</div>

  return (
    <div>
      <div className="flex items-center justify-between mb-4">
        <h2 className="text-xl font-semibold">Calendar</h2>
        <div className="flex items-center gap-4">
          <div className="flex items-center gap-2">
            <button
              onClick={() => navigateDate(-1)}
              className="bg-bg-tertiary px-3 py-1 rounded hover:bg-gray-600"
            >
              ←
            </button>
            <button
              onClick={() => setCurrentDate(new Date())}
              className="bg-bg-tertiary px-3 py-1 rounded hover:bg-gray-600 text-sm"
            >
              Today
            </button>
            <button
              onClick={() => navigateDate(1)}
              className="bg-bg-tertiary px-3 py-1 rounded hover:bg-gray-600"
            >
              →
            </button>
          </div>
          <select
            value={view}
            onChange={(e) => setView(e.target.value as CalendarView)}
            className="bg-bg-tertiary border border-gray-600 rounded px-2 py-1 text-sm"
          >
            <option value="1day">1 Day</option>
            <option value="2day">2 Days</option>
            <option value="week">Week</option>
            <option value="month">Month</option>
          </select>
          <button
            onClick={() => setShowEventForm(true)}
            className="bg-primary text-black px-3 py-1 rounded hover:bg-primary/90"
          >
            + Add Event
          </button>
        </div>
      </div>

      {view === 'month' ? (
        <MonthView
          currentDate={currentDate}
          events={events}
          onEventClick={(e) => {
            setEditingEvent(e)
            setShowEventForm(true)
          }}
        />
      ) : (
        <div className="flex gap-2">
          {getDaysToShow().map((day) => (
            <div key={day.toISOString()} className="flex-1 min-w-0">
              <div className="text-center mb-2">
                <div className="text-sm text-gray-400">
                  {day.toLocaleDateString('en-US', { weekday: 'short' })}
                </div>
                <div className={`text-lg font-semibold ${
                  day.toDateString() === new Date().toDateString() ? 'text-primary' : ''
                }`}>
                  {day.getDate()}
                </div>
              </div>
              <div className="space-y-1">
                {getEventsForDay(day).map((event) => (
                  <div
                    key={event.id}
                    onClick={() => {
                      setEditingEvent(event)
                      setShowEventForm(true)
                    }}
                    className="bg-primary/20 border-l-2 border-primary rounded p-2 cursor-pointer hover:bg-primary/30"
                  >
                    <div className="text-xs text-primary">{formatTime(event.start_datetime)}</div>
                    <div className="text-sm truncate">{event.title}</div>
                  </div>
                ))}
              </div>
            </div>
          ))}
        </div>
      )}

      {showEventForm && (
        <EventForm
          event={editingEvent || undefined}
          onSubmit={handleEventSubmit}
          onCancel={() => {
            setShowEventForm(false)
            setEditingEvent(null)
          }}
        />
      )}
    </div>
  )
}

function MonthView({
  currentDate,
  events,
  onEventClick
}: {
  currentDate: Date
  events: Event[]
  onEventClick: (event: Event) => void
}) {
  const year = currentDate.getFullYear()
  const month = currentDate.getMonth()

  const firstDay = new Date(year, month, 1)
  const lastDay = new Date(year, month + 1, 0)
  const startDate = new Date(firstDay)
  startDate.setDate(startDate.getDate() - firstDay.getDay())

  const weeks: Date[][] = []
  let currentWeek: Date[] = []
  const current = new Date(startDate)

  while (current <= lastDay || currentWeek.length > 0) {
    currentWeek.push(new Date(current))
    current.setDate(current.getDate() + 1)

    if (currentWeek.length === 7) {
      weeks.push(currentWeek)
      currentWeek = []
      if (current > lastDay) break
    }
  }

  const getEventsForDay = (date: Date) => {
    const dateStr = date.toISOString().split('T')[0]
    return events.filter(e => e.start_datetime.split('T')[0] === dateStr)
  }

  return (
    <div>
      <div className="text-center mb-4 font-semibold">
        {currentDate.toLocaleDateString('en-US', { month: 'long', year: 'numeric' })}
      </div>
      <div className="grid grid-cols-7 gap-1">
        {['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'].map((day) => (
          <div key={day} className="text-center text-xs text-gray-400 py-2">
            {day}
          </div>
        ))}
        {weeks.flat().map((day, i) => {
          const isCurrentMonth = day.getMonth() === month
          const dayEvents = getEventsForDay(day)

          return (
            <div
              key={i}
              className={`min-h-[80px] p-1 border border-gray-700 ${
                isCurrentMonth ? 'bg-bg-tertiary' : 'bg-bg-primary'
              }`}
            >
              <div className={`text-xs ${
                day.toDateString() === new Date().toDateString()
                  ? 'text-primary font-bold'
                  : isCurrentMonth ? 'text-white' : 'text-gray-600'
              }`}>
                {day.getDate()}
              </div>
              {dayEvents.slice(0, 2).map((event) => (
                <div
                  key={event.id}
                  onClick={() => onEventClick(event)}
                  className="text-xs bg-primary/20 text-primary truncate rounded px-1 mt-1 cursor-pointer hover:bg-primary/30"
                >
                  {event.title}
                </div>
              ))}
              {dayEvents.length > 2 && (
                <div className="text-xs text-gray-500 mt-1">
                  +{dayEvents.length - 2} more
                </div>
              )}
            </div>
          )
        })}
      </div>
    </div>
  )
}
