import { useRef, useEffect } from 'react'

interface RichTextEditorProps {
  content: string
  onChange: (content: string) => void
  placeholder?: string
}

export default function RichTextEditor({ content, onChange, placeholder }: RichTextEditorProps) {
  const editorRef = useRef<HTMLDivElement>(null)

  useEffect(() => {
    if (editorRef.current && editorRef.current.innerHTML !== content) {
      editorRef.current.innerHTML = content
    }
  }, [content])

  const handleInput = () => {
    if (editorRef.current) {
      onChange(editorRef.current.innerHTML)
    }
  }

  const execCommand = (command: string, value?: string) => {
    document.execCommand(command, false, value)
    editorRef.current?.focus()
  }

  const colors = ['#ffffff', '#32CD32', '#ff6b6b', '#4dabf7', '#ffd43b', '#a9a9a9']
  const fontSizes = [
    { label: 'Small', value: '2' },
    { label: 'Normal', value: '3' },
    { label: 'Large', value: '4' },
    { label: 'Huge', value: '6' },
  ]

  return (
    <div className="flex flex-col h-full">
      {/* Toolbar */}
      <div className="flex items-center gap-1 p-2 bg-bg-tertiary border-b border-gray-600 flex-wrap">
        <button
          onClick={() => execCommand('bold')}
          className="px-2 py-1 hover:bg-bg-secondary rounded font-bold"
          title="Bold"
        >
          B
        </button>
        <button
          onClick={() => execCommand('italic')}
          className="px-2 py-1 hover:bg-bg-secondary rounded italic"
          title="Italic"
        >
          I
        </button>
        <button
          onClick={() => execCommand('underline')}
          className="px-2 py-1 hover:bg-bg-secondary rounded underline"
          title="Underline"
        >
          U
        </button>

        <div className="w-px h-6 bg-gray-600 mx-1" />

        <button
          onClick={() => execCommand('insertUnorderedList')}
          className="px-2 py-1 hover:bg-bg-secondary rounded"
          title="Bullet List"
        >
          •
        </button>
        <button
          onClick={() => execCommand('insertOrderedList')}
          className="px-2 py-1 hover:bg-bg-secondary rounded"
          title="Numbered List"
        >
          1.
        </button>

        <div className="w-px h-6 bg-gray-600 mx-1" />

        <select
          onChange={(e) => execCommand('fontSize', e.target.value)}
          className="bg-bg-secondary border border-gray-600 rounded px-1 py-1 text-sm"
          defaultValue="3"
        >
          {fontSizes.map((size) => (
            <option key={size.value} value={size.value}>
              {size.label}
            </option>
          ))}
        </select>

        <div className="w-px h-6 bg-gray-600 mx-1" />

        <div className="flex items-center gap-1">
          {colors.map((color) => (
            <button
              key={color}
              onClick={() => execCommand('foreColor', color)}
              className="w-5 h-5 rounded border border-gray-600 hover:scale-110 transition-transform"
              style={{ backgroundColor: color }}
              title={`Text color: ${color}`}
            />
          ))}
        </div>
      </div>

      {/* Editor */}
      <div
        ref={editorRef}
        contentEditable
        onInput={handleInput}
        className="flex-1 p-4 overflow-y-auto focus:outline-none"
        style={{ minHeight: '200px' }}
        data-placeholder={placeholder}
      />

      <style>{`
        [contenteditable]:empty:before {
          content: attr(data-placeholder);
          color: #6b7280;
          pointer-events: none;
        }
      `}</style>
    </div>
  )
}
