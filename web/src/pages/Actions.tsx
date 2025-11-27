import { useState } from 'react'
import { useFolders, useCreateFolder, useDeleteFolder } from '../hooks/useFolders'
import { useActions, useCreateAction, useUpdateAction, useDeleteAction, useCompleteAction } from '../hooks/useActions'
import ActionForm from '../components/ActionForm'
import type { ActionFormData } from '../components/ActionForm'
import type { Action, Folder } from '../types'

export default function Actions() {
  const [selectedFolderId, setSelectedFolderId] = useState<string | null>(null)
  const [showActionForm, setShowActionForm] = useState(false)
  const [editingAction, setEditingAction] = useState<Action | null>(null)
  const [showFolderInput, setShowFolderInput] = useState(false)
  const [newFolderName, setNewFolderName] = useState('')
  const [actionMenuId, setActionMenuId] = useState<string | null>(null)

  const { data: folders = [], isLoading: foldersLoading } = useFolders()
  const { data: actions = [], isLoading: actionsLoading } = useActions(selectedFolderId || undefined)

  const createFolder = useCreateFolder()
  const deleteFolder = useDeleteFolder()
  const createAction = useCreateAction()
  const updateAction = useUpdateAction()
  const deleteAction = useDeleteAction()
  const completeAction = useCompleteAction()

  // Auto-select first folder
  if (!selectedFolderId && folders.length > 0) {
    setSelectedFolderId(folders[0].id)
  }

  const handleCreateFolder = async (e: React.FormEvent) => {
    e.preventDefault()
    if (!newFolderName.trim()) return

    await createFolder.mutateAsync(newFolderName)
    setNewFolderName('')
    setShowFolderInput(false)
  }

  const handleDeleteFolder = async (folder: Folder) => {
    if (folder.is_default) {
      alert('Cannot delete default folder')
      return
    }
    if (confirm(`Delete folder "${folder.name}" and all its actions?`)) {
      await deleteFolder.mutateAsync(folder.id)
      if (selectedFolderId === folder.id) {
        setSelectedFolderId(folders.find(f => f.id !== folder.id)?.id || null)
      }
    }
  }

  const handleActionSubmit = async (data: ActionFormData) => {
    if (editingAction) {
      await updateAction.mutateAsync({ id: editingAction.id, ...data })
    } else {
      await createAction.mutateAsync(data)
    }
    setShowActionForm(false)
    setEditingAction(null)
  }

  const handleCompleteAction = async (action: Action) => {
    await completeAction.mutateAsync({
      actionId: action.id,
      isRecurring: action.is_recurring
    })
  }

  const formatDueDate = (date: string | null, time: string | null) => {
    if (!date) return null
    const d = new Date(date)
    const dateStr = d.toLocaleDateString('en-US', { month: 'short', day: 'numeric' })
    return time ? `${dateStr} ${time}` : dateStr
  }

  if (foldersLoading) {
    return <div className="text-gray-400">Loading...</div>
  }

  return (
    <div className="flex gap-4 h-[calc(100vh-120px)]">
      {/* Folders Sidebar */}
      <aside className="w-64 bg-bg-secondary rounded-lg p-4 flex flex-col">
        <div className="flex items-center justify-between mb-4">
          <h2 className="font-semibold">Folders</h2>
          <button
            onClick={() => setShowFolderInput(true)}
            className="text-primary hover:text-primary/80 text-xl"
          >
            +
          </button>
        </div>

        {showFolderInput && (
          <form onSubmit={handleCreateFolder} className="mb-4">
            <input
              type="text"
              value={newFolderName}
              onChange={(e) => setNewFolderName(e.target.value)}
              placeholder="Folder name"
              className="w-full bg-bg-tertiary border border-gray-600 rounded px-3 py-2 text-sm text-white focus:outline-none focus:border-primary"
              autoFocus
              onBlur={() => {
                if (!newFolderName.trim()) setShowFolderInput(false)
              }}
            />
          </form>
        )}

        <div className="space-y-1 flex-1 overflow-y-auto">
          {folders.map((folder) => (
            <div
              key={folder.id}
              className={`group flex items-center justify-between rounded px-3 py-2 cursor-pointer ${
                selectedFolderId === folder.id
                  ? 'bg-primary/20 text-primary'
                  : 'hover:bg-bg-tertiary'
              }`}
              onClick={() => setSelectedFolderId(folder.id)}
            >
              <span className="truncate">{folder.name}</span>
              {!folder.is_default && (
                <button
                  onClick={(e) => {
                    e.stopPropagation()
                    handleDeleteFolder(folder)
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

      {/* Actions List */}
      <main className="flex-1 bg-bg-secondary rounded-lg p-4 flex flex-col">
        <div className="flex items-center justify-between mb-4">
          <h2 className="text-xl font-semibold">
            {folders.find(f => f.id === selectedFolderId)?.name || 'Actions'}
          </h2>
          <button
            onClick={() => setShowActionForm(true)}
            className="bg-primary text-black px-3 py-1 rounded hover:bg-primary/90"
          >
            + Add Action
          </button>
        </div>

        {actionsLoading ? (
          <div className="text-gray-400">Loading...</div>
        ) : actions.length === 0 ? (
          <p className="text-gray-400">No actions in this folder</p>
        ) : (
          <div className="space-y-2 flex-1 overflow-y-auto">
            {actions.map((action) => (
              <div
                key={action.id}
                className="bg-bg-tertiary rounded p-3 flex items-start gap-3"
              >
                <button
                  onClick={() => handleCompleteAction(action)}
                  className="mt-1 w-5 h-5 rounded border-2 border-gray-500 hover:border-primary flex-shrink-0"
                />
                <div className="flex-1 min-w-0">
                  <div className="font-medium">{action.title}</div>
                  {action.description && (
                    <div className="text-sm text-gray-400 truncate">{action.description}</div>
                  )}
                  <div className="flex gap-2 mt-1 text-xs">
                    {action.due_date && (
                      <span className="text-primary">
                        {formatDueDate(action.due_date, action.due_time)}
                      </span>
                    )}
                    {action.is_recurring && (
                      <span className="text-gray-500">
                        {action.recurrence_type}
                      </span>
                    )}
                  </div>
                </div>
                <div className="relative">
                  <button
                    onClick={() => setActionMenuId(actionMenuId === action.id ? null : action.id)}
                    className="text-gray-400 hover:text-white p-1"
                  >
                    ⋮
                  </button>
                  {actionMenuId === action.id && (
                    <div className="absolute right-0 top-8 bg-bg-primary border border-gray-600 rounded shadow-lg py-1 z-10">
                      <button
                        onClick={() => {
                          setEditingAction(action)
                          setShowActionForm(true)
                          setActionMenuId(null)
                        }}
                        className="block w-full text-left px-4 py-2 hover:bg-bg-tertiary text-sm"
                      >
                        Edit
                      </button>
                      <button
                        onClick={async () => {
                          if (confirm('Delete this action?')) {
                            await deleteAction.mutateAsync(action.id)
                          }
                          setActionMenuId(null)
                        }}
                        className="block w-full text-left px-4 py-2 hover:bg-bg-tertiary text-sm text-red-400"
                      >
                        Delete
                      </button>
                    </div>
                  )}
                </div>
              </div>
            ))}
          </div>
        )}
      </main>

      {/* Action Form Modal */}
      {showActionForm && (
        <ActionForm
          folders={folders}
          action={editingAction || undefined}
          defaultFolderId={selectedFolderId || undefined}
          onSubmit={handleActionSubmit}
          onCancel={() => {
            setShowActionForm(false)
            setEditingAction(null)
          }}
        />
      )}
    </div>
  )
}
