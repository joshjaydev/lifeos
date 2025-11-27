import { Routes, Route, Navigate } from 'react-router-dom'
import { useAuth } from './contexts/AuthContext'
import Login from './pages/Login'
import AuthCallback from './pages/AuthCallback'
import Layout from './components/Layout'
import Time from './pages/Time'
import Notes from './pages/Notes'
import Values from './pages/Values'
import Actions from './pages/Actions'

function ProtectedRoute({ children }: { children: React.ReactNode }) {
  const { user, loading } = useAuth()

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <div className="text-primary">Loading...</div>
      </div>
    )
  }

  if (!user) {
    return <Navigate to="/login" replace />
  }

  return <>{children}</>
}

function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/auth/callback" element={<AuthCallback />} />
      <Route
        path="/"
        element={
          <ProtectedRoute>
            <Layout />
          </ProtectedRoute>
        }
      >
        <Route index element={<Navigate to="/time" replace />} />
        <Route path="time" element={<Time />} />
        <Route path="notes" element={<Notes />} />
        <Route path="values" element={<Values />} />
        <Route path="actions" element={<Actions />} />
      </Route>
    </Routes>
  )
}

export default App
