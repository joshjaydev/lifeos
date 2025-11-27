import { useState } from 'react'
import { usePrinciples, useCreatePrinciple, useUpdatePrinciple, useDeletePrinciple } from '../hooks/usePrinciples'
import { useGoals, useCreateGoal, useUpdateGoal, useDeleteGoal } from '../hooks/useGoals'
import { useActions } from '../hooks/useActions'
import type { Principle, Goal } from '../types'

type Tab = 'principles' | 'goals'

export default function Values() {
  const [activeTab, setActiveTab] = useState<Tab>('principles')

  return (
    <div>
      <div className="flex gap-2 mb-6">
        <button
          onClick={() => setActiveTab('principles')}
          className={`px-4 py-2 rounded ${
            activeTab === 'principles'
              ? 'bg-primary text-black font-semibold'
              : 'bg-bg-tertiary text-gray-300 hover:bg-bg-secondary'
          }`}
        >
          Principles
        </button>
        <button
          onClick={() => setActiveTab('goals')}
          className={`px-4 py-2 rounded ${
            activeTab === 'goals'
              ? 'bg-primary text-black font-semibold'
              : 'bg-bg-tertiary text-gray-300 hover:bg-bg-secondary'
          }`}
        >
          Goals
        </button>
      </div>

      <div className="bg-bg-secondary rounded-lg p-6">
        {activeTab === 'principles' && <Principles />}
        {activeTab === 'goals' && <Goals />}
      </div>
    </div>
  )
}

function Principles() {
  const [showForm, setShowForm] = useState(false)
  const [editingPrinciple, setEditingPrinciple] = useState<Principle | null>(null)
  const [parentId, setParentId] = useState<string | null>(null)
  const [formData, setFormData] = useState({ fundamental_truth: '', experience: '' })

  const { data: principles = [], isLoading } = usePrinciples()
  const createPrinciple = useCreatePrinciple()
  const updatePrinciple = useUpdatePrinciple()
  const deletePrinciple = useDeletePrinciple()

  // Build tree structure
  const rootPrinciples = principles.filter(p => !p.parent_id)
  const getChildren = (parentId: string) => principles.filter(p => p.parent_id === parentId)

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    if (!formData.fundamental_truth.trim()) return

    if (editingPrinciple) {
      await updatePrinciple.mutateAsync({
        id: editingPrinciple.id,
        fundamental_truth: formData.fundamental_truth,
        experience: formData.experience || undefined
      })
    } else {
      await createPrinciple.mutateAsync({
        fundamental_truth: formData.fundamental_truth,
        experience: formData.experience || undefined,
        parent_id: parentId || undefined
      })
    }

    resetForm()
  }

  const resetForm = () => {
    setShowForm(false)
    setEditingPrinciple(null)
    setParentId(null)
    setFormData({ fundamental_truth: '', experience: '' })
  }

  const handleEdit = (principle: Principle) => {
    setEditingPrinciple(principle)
    setFormData({
      fundamental_truth: principle.fundamental_truth,
      experience: principle.experience || ''
    })
    setShowForm(true)
  }

  const handleAddSub = (parentId: string) => {
    setParentId(parentId)
    setFormData({ fundamental_truth: '', experience: '' })
    setShowForm(true)
  }

  const handleDelete = async (principle: Principle) => {
    const children = getChildren(principle.id)
    const message = children.length > 0
      ? `Delete this principle and its ${children.length} sub-principle(s)?`
      : 'Delete this principle?'

    if (confirm(message)) {
      await deletePrinciple.mutateAsync(principle.id)
    }
  }

  const renderPrinciple = (principle: Principle, depth: number = 0) => {
    const children = getChildren(principle.id)

    return (
      <div key={principle.id} className={`${depth > 0 ? 'ml-6 border-l-2 border-gray-700 pl-4' : ''}`}>
        <div className="bg-bg-tertiary rounded p-4 mb-2">
          <div className="flex items-start justify-between gap-4">
            <div className="flex-1">
              <p className="font-medium">{principle.fundamental_truth}</p>
              {principle.experience && (
                <p className="text-sm text-gray-400 mt-2 italic">
                  "{principle.experience}"
                </p>
              )}
            </div>
            <div className="flex gap-1">
              <button
                onClick={() => handleAddSub(principle.id)}
                className="text-gray-400 hover:text-primary text-sm px-2"
                title="Add sub-principle"
              >
                +
              </button>
              <button
                onClick={() => handleEdit(principle)}
                className="text-gray-400 hover:text-white text-sm px-2"
              >
                Edit
              </button>
              <button
                onClick={() => handleDelete(principle)}
                className="text-gray-400 hover:text-red-400 text-sm px-2"
              >
                Delete
              </button>
            </div>
          </div>
        </div>
        {children.map(child => renderPrinciple(child, depth + 1))}
      </div>
    )
  }

  if (isLoading) return <div className="text-gray-400">Loading...</div>

  return (
    <div>
      <div className="flex items-center justify-between mb-4">
        <h2 className="text-xl font-semibold">Principles</h2>
        <button
          onClick={() => {
            setParentId(null)
            setShowForm(true)
          }}
          className="bg-primary text-black px-3 py-1 rounded hover:bg-primary/90"
        >
          + Add Principle
        </button>
      </div>

      {showForm && (
        <form onSubmit={handleSubmit} className="bg-bg-tertiary rounded p-4 mb-4">
          <h3 className="font-medium mb-3">
            {editingPrinciple ? 'Edit Principle' : parentId ? 'Add Sub-Principle' : 'New Principle'}
          </h3>
          <div className="space-y-3">
            <div>
              <label className="block text-sm text-gray-400 mb-1">Fundamental Truth</label>
              <textarea
                value={formData.fundamental_truth}
                onChange={(e) => setFormData({ ...formData, fundamental_truth: e.target.value })}
                className="w-full bg-bg-secondary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
                rows={2}
                required
                placeholder="What is the principle?"
              />
            </div>
            <div>
              <label className="block text-sm text-gray-400 mb-1">Experience/Failure (optional)</label>
              <textarea
                value={formData.experience}
                onChange={(e) => setFormData({ ...formData, experience: e.target.value })}
                className="w-full bg-bg-secondary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
                rows={2}
                placeholder="What led to this principle?"
              />
            </div>
            <div className="flex gap-2">
              <button
                type="button"
                onClick={resetForm}
                className="flex-1 bg-bg-secondary text-gray-300 py-2 rounded hover:bg-gray-600"
              >
                Cancel
              </button>
              <button
                type="submit"
                className="flex-1 bg-primary text-black font-semibold py-2 rounded hover:bg-primary/90"
              >
                {editingPrinciple ? 'Save' : 'Create'}
              </button>
            </div>
          </div>
        </form>
      )}

      {rootPrinciples.length === 0 ? (
        <p className="text-gray-400">No principles yet. Add your first one!</p>
      ) : (
        <div className="space-y-2">
          {rootPrinciples.map(p => renderPrinciple(p))}
        </div>
      )}
    </div>
  )
}

function Goals() {
  const [showForm, setShowForm] = useState(false)
  const [editingGoal, setEditingGoal] = useState<Goal | null>(null)
  const [goalText, setGoalText] = useState('')

  const { data: goals = [], isLoading } = useGoals()
  const createGoal = useCreateGoal()
  const updateGoal = useUpdateGoal()
  const deleteGoal = useDeleteGoal()

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    if (!goalText.trim()) return

    if (editingGoal) {
      await updateGoal.mutateAsync({ id: editingGoal.id, what_do_you_want: goalText })
    } else {
      await createGoal.mutateAsync(goalText)
    }

    resetForm()
  }

  const resetForm = () => {
    setShowForm(false)
    setEditingGoal(null)
    setGoalText('')
  }

  const handleEdit = (goal: Goal) => {
    setEditingGoal(goal)
    setGoalText(goal.what_do_you_want)
    setShowForm(true)
  }

  const handleDelete = async (goal: Goal) => {
    if (confirm(`Delete goal "${goal.what_do_you_want}"? This will also delete the associated folder and notebook.`)) {
      await deleteGoal.mutateAsync(goal.id)
    }
  }

  if (isLoading) return <div className="text-gray-400">Loading...</div>

  return (
    <div>
      <div className="flex items-center justify-between mb-4">
        <h2 className="text-xl font-semibold">Goals</h2>
        <button
          onClick={() => setShowForm(true)}
          className="bg-primary text-black px-3 py-1 rounded hover:bg-primary/90"
        >
          + Add Goal
        </button>
      </div>

      {showForm && (
        <form onSubmit={handleSubmit} className="bg-bg-tertiary rounded p-4 mb-4">
          <h3 className="font-medium mb-3">
            {editingGoal ? 'Edit Goal' : 'New Goal'}
          </h3>
          <div className="space-y-3">
            <div>
              <label className="block text-sm text-gray-400 mb-1">What do you want?</label>
              <input
                type="text"
                value={goalText}
                onChange={(e) => setGoalText(e.target.value)}
                className="w-full bg-bg-secondary border border-gray-600 rounded px-3 py-2 text-white focus:outline-none focus:border-primary"
                required
                placeholder="Describe your goal..."
              />
            </div>
            <p className="text-xs text-gray-500">
              Creating a goal will automatically create a folder in Actions and a notebook in Notes.
            </p>
            <div className="flex gap-2">
              <button
                type="button"
                onClick={resetForm}
                className="flex-1 bg-bg-secondary text-gray-300 py-2 rounded hover:bg-gray-600"
              >
                Cancel
              </button>
              <button
                type="submit"
                className="flex-1 bg-primary text-black font-semibold py-2 rounded hover:bg-primary/90"
              >
                {editingGoal ? 'Save' : 'Create'}
              </button>
            </div>
          </div>
        </form>
      )}

      {goals.length === 0 ? (
        <p className="text-gray-400">No goals yet. What do you want to achieve?</p>
      ) : (
        <div className="space-y-3">
          {goals.map((goal) => (
            <GoalCard
              key={goal.id}
              goal={goal}
              onEdit={() => handleEdit(goal)}
              onDelete={() => handleDelete(goal)}
            />
          ))}
        </div>
      )}
    </div>
  )
}

function GoalCard({
  goal,
  onEdit,
  onDelete
}: {
  goal: Goal
  onEdit: () => void
  onDelete: () => void
}) {
  const [expanded, setExpanded] = useState(false)
  const { data: actions = [] } = useActions(goal.folder_id || undefined)

  return (
    <div className="bg-bg-tertiary rounded p-4">
      <div className="flex items-start justify-between gap-4">
        <div className="flex-1">
          <button
            onClick={() => setExpanded(!expanded)}
            className="flex items-center gap-2 text-left"
          >
            <span className={`transform transition-transform ${expanded ? 'rotate-90' : ''}`}>
              ▶
            </span>
            <span className="font-medium">{goal.what_do_you_want}</span>
          </button>
          <div className="flex gap-4 mt-2 text-xs text-gray-500">
            <span>📁 Folder created</span>
            <span>📓 Notebook created</span>
            <span>{actions.length} action(s)</span>
          </div>
        </div>
        <div className="flex gap-1">
          <button
            onClick={onEdit}
            className="text-gray-400 hover:text-white text-sm px-2"
          >
            Edit
          </button>
          <button
            onClick={onDelete}
            className="text-gray-400 hover:text-red-400 text-sm px-2"
          >
            Delete
          </button>
        </div>
      </div>

      {expanded && (
        <div className="mt-4 pl-6 border-l-2 border-gray-700">
          {actions.length === 0 ? (
            <p className="text-sm text-gray-500">
              No actions yet. Go to Actions → {goal.what_do_you_want} folder to add actions.
            </p>
          ) : (
            <div className="space-y-2">
              {actions.map((action) => (
                <div key={action.id} className="text-sm flex items-center gap-2">
                  <span className="w-3 h-3 rounded border border-gray-500" />
                  <span>{action.title}</span>
                </div>
              ))}
            </div>
          )}
        </div>
      )}
    </div>
  )
}
