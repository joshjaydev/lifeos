import { useState, useEffect } from 'react'
import { useNotebooks, useCreateNotebook, useDeleteNotebook } from '../hooks/useNotebooks'
import { useNotes, useCreateNote, useUpdateNote, useDeleteNote, useJournalNote } from '../hooks/useNotes'
import RichTextEditor from '../components/RichTextEditor'
import type { Note, Notebook } from '../types'

export default function Notes() {
  const [selectedNotebookId, setSelectedNotebookId] = useState<string | null>(null)
  const [selectedNote, setSelectedNote] = useState<Note | null>(null)
  const [showNotebookInput, setShowNotebookInput] = useState(false)
  const [newNotebookName, setNewNotebookName] = useState('')
  const [isEditing, setIsEditing] = useState(false)
  const [editContent, setEditContent] = useState('')
  const [editTitle, setEditTitle] = useState('')

  const { data: notebooks = [], isLoading: notebooksLoading } = useNotebooks()
  const { data: notes = [], isLoading: notesLoading } = useNotes(selectedNotebookId || undefined)

  const createNotebook = useCreateNotebook()
  const deleteNotebook = useDeleteNotebook()
  const createNote = useCreateNote()
  const updateNote = useUpdateNote()
  const deleteNote = useDeleteNote()

  // Auto-select first notebook
  useEffect(() => {
    if (!selectedNotebookId && notebooks.length > 0) {
      setSelectedNotebookId(notebooks[0].id)
    }
  }, [notebooks, selectedNotebookId])

  const handleCreateNotebook = async (e: React.FormEvent) => {
    e.preventDefault()
    if (!newNotebookName.trim()) return

    const notebook = await createNotebook.mutateAsync(newNotebookName)
    setNewNotebookName('')
    setShowNotebookInput(false)
    setSelectedNotebookId(notebook.id)
  }

  const handleDeleteNotebook = async (notebook: Notebook) => {
    if (notebook.is_default) {
      alert('Cannot delete default notebook')
      return
    }
    if (confirm(`Delete notebook "${notebook.name}" and all its notes?`)) {
      await deleteNotebook.mutateAsync(notebook.id)
      if (selectedNotebookId === notebook.id) {
        setSelectedNotebookId(notebooks.find(n => n.id !== notebook.id)?.id || null)
      }
      setSelectedNote(null)
    }
  }

  const handleCreateNote = async () => {
    if (!selectedNotebookId) return

    const note = await createNote.mutateAsync({
      notebook_id: selectedNotebookId,
      title: 'Untitled Note',
      content: { html: '' }
    })

    setSelectedNote(note)
    setEditTitle('Untitled Note')
    setEditContent('')
    setIsEditing(true)
  }

  const handleSelectNote = (note: Note) => {
    if (isEditing && selectedNote) {
      handleSaveNote()
    }
    setSelectedNote(note)
    setEditTitle(note.title)
    setEditContent((note.content as { html?: string })?.html || '')
    setIsEditing(false)
  }

  const handleSaveNote = async () => {
    if (!selectedNote) return

    await updateNote.mutateAsync({
      id: selectedNote.id,
      title: editTitle,
      content: { html: editContent }
    })
    setIsEditing(false)
  }

  const handleDeleteNote = async (note: Note) => {
    if (note.is_journal) {
      alert('Cannot delete Journal. You can clear its content instead.')
      return
    }
    if (confirm(`Delete "${note.title}"?`)) {
      await deleteNote.mutateAsync(note.id)
      if (selectedNote?.id === note.id) {
        setSelectedNote(null)
        setIsEditing(false)
      }
    }
  }

  // Find the Journal note in the selected notebook
  const journalNote = notes.find(n => n.is_journal)

  if (notebooksLoading) {
    return <div className="text-gray-400">Loading...</div>
  }

  return (
    <div className="flex gap-4 h-[calc(100vh-120px)]">
      {/* Notebooks Sidebar */}
      <aside className="w-64 bg-bg-secondary rounded-lg p-4 flex flex-col">
        <div className="flex items-center justify-between mb-4">
          <h2 className="font-semibold">Notebooks</h2>
          <button
            onClick={() => setShowNotebookInput(true)}
            className="text-primary hover:text-primary/80 text-xl"
          >
            +
          </button>
        </div>

        {showNotebookInput && (
          <form onSubmit={handleCreateNotebook} className="mb-4">
            <input
              type="text"
              value={newNotebookName}
              onChange={(e) => setNewNotebookName(e.target.value)}
              placeholder="Notebook name"
              className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-sm text-white focus:outline-none focus:border-primary"
              autoFocus
              onBlur={() => {
                if (!newNotebookName.trim()) setShowNotebookInput(false)
              }}
            />
          </form>
        )}

        <div className="space-y-1 flex-1 overflow-y-auto">
          {notebooks.map((notebook) => (
            <div
              key={notebook.id}
              className={`group flex items-center justify-between rounded px-3 py-2 cursor-pointer ${
                selectedNotebookId === notebook.id
                  ? 'bg-primary/20 text-primary'
                  : 'hover:bg-bg-tertiary'
              }`}
              onClick={() => {
                setSelectedNotebookId(notebook.id)
                setSelectedNote(null)
                setIsEditing(false)
              }}
            >
              <span className="truncate">{notebook.name}</span>
              {!notebook.is_default && !notebook.goal_id && (
                <button
                  onClick={(e) => {
                    e.stopPropagation()
                    handleDeleteNotebook(notebook)
                  }}
                  className="opacity-0 group-hover:opacity-100 text-gray-400 hover:text-red-400 text-sm"
                >
                  ×
                </button>
              )}
            </div>
          ))}
        </div>
      </aside>

      {/* Notes List */}
      <div className="w-64 bg-bg-secondary rounded-lg p-4 flex flex-col">
        <div className="flex items-center justify-between mb-4">
          <h2 className="font-semibold">Notes</h2>
        </div>

        {notesLoading ? (
          <div className="text-gray-400 text-sm">Loading...</div>
        ) : (
          <div className="space-y-1 flex-1 overflow-y-auto">
            {/* Journal Entry for Today */}
            {journalNote && (
              <JournalEntry
                notebook={notebooks.find(n => n.id === selectedNotebookId)!}
                onSelect={(note) => handleSelectNote(note)}
                isSelected={selectedNote?.is_journal && selectedNote?.journal_date === new Date().toISOString().split('T')[0]}
              />
            )}

            {/* Regular Notes */}
            {notes
              .filter(n => !n.is_journal)
              .map((note) => (
                <div
                  key={note.id}
                  className={`group flex items-center justify-between rounded px-3 py-2 cursor-pointer ${
                    selectedNote?.id === note.id
                      ? 'bg-primary/20 text-primary'
                      : 'hover:bg-bg-tertiary'
                  }`}
                  onClick={() => handleSelectNote(note)}
                >
                  <span className="truncate">{note.title}</span>
                  <button
                    onClick={(e) => {
                      e.stopPropagation()
                      handleDeleteNote(note)
                    }}
                    className="opacity-0 group-hover:opacity-100 text-gray-400 hover:text-red-400 text-sm"
                  >
                    ×
                  </button>
                </div>
              ))}
          </div>
        )}

        {/* Create Note Button */}
        <button
          onClick={handleCreateNote}
          className="mt-4 bg-primary text-black py-2 rounded hover:bg-primary/90 flex items-center justify-center gap-2"
        >
          <span>✎</span> New Note
        </button>
      </div>

      {/* Note Editor */}
      <main className="flex-1 bg-bg-secondary rounded-lg flex flex-col overflow-hidden">
        {selectedNote ? (
          <>
            <div className="flex items-center justify-between p-4 border-b border-gray-700">
              {isEditing ? (
                <input
                  type="text"
                  value={editTitle}
                  onChange={(e) => setEditTitle(e.target.value)}
                  className="flex-1 bg-transparent text-xl font-semibold focus:outline-none"
                  placeholder="Note title"
                />
              ) : (
                <h2 className="text-xl font-semibold">{selectedNote.title}</h2>
              )}
              <div className="flex gap-2">
                {isEditing ? (
                  <button
                    onClick={handleSaveNote}
                    className="bg-primary text-black px-3 py-1 rounded hover:bg-primary/90 text-sm"
                  >
                    Save
                  </button>
                ) : (
                  <button
                    onClick={() => setIsEditing(true)}
                    className="bg-bg-tertiary px-3 py-1 rounded hover:bg-gray-600 text-sm"
                  >
                    Edit
                  </button>
                )}
              </div>
            </div>
            {isEditing ? (
              <RichTextEditor
                content={editContent}
                onChange={setEditContent}
                placeholder="Start writing..."
              />
            ) : (
              <div
                className="flex-1 p-4 overflow-y-auto prose prose-invert max-w-none"
                dangerouslySetInnerHTML={{
                  __html: (selectedNote.content as { html?: string })?.html || '<p class="text-gray-500">Empty note</p>'
                }}
              />
            )}
          </>
        ) : (
          <div className="flex-1 flex items-center justify-center text-gray-500">
            Select a note or create a new one
          </div>
        )}
      </main>
    </div>
  )
}

function JournalEntry({
  notebook,
  onSelect,
  isSelected
}: {
  notebook: Notebook
  onSelect: (note: Note) => void
  isSelected: boolean
}) {
  const today = new Date().toISOString().split('T')[0]
  const { data: journalNote } = useJournalNote(notebook.id, today)
  const createNote = useCreateNote()

  const handleClick = async () => {
    if (journalNote) {
      onSelect(journalNote)
    } else {
      // Create today's journal entry
      const note = await createNote.mutateAsync({
        notebook_id: notebook.id,
        title: `Journal - ${new Date().toLocaleDateString('en-US', { month: 'long', day: 'numeric', year: 'numeric' })}`,
        content: { html: '' },
        is_journal: true,
        journal_date: today
      })
      onSelect(note)
    }
  }

  return (
    <div
      className={`flex items-center gap-2 rounded px-3 py-2 cursor-pointer ${
        isSelected
          ? 'bg-primary/20 text-primary'
          : 'hover:bg-bg-tertiary'
      }`}
      onClick={handleClick}
    >
      <span>📓</span>
      <span className="truncate">Today's Journal</span>
    </div>
  )
}
