import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { supabase } from '../lib/supabase'

export default function AuthCallback() {
  const navigate = useNavigate()

  useEffect(() => {
    const handleCallback = async () => {
      // Check for code in URL (PKCE flow)
      const params = new URLSearchParams(window.location.search)
      const code = params.get('code')

      if (code) {
        const { error } = await supabase.auth.exchangeCodeForSession(code)
        if (error) {
          console.error('Auth error:', error)
          navigate('/login')
          return
        }
      }

      // Check for hash tokens (implicit flow)
      if (window.location.hash) {
        const { data, error } = await supabase.auth.getSession()
        if (error) {
          console.error('Auth error:', error)
          navigate('/login')
          return
        }
        if (data.session) {
          navigate('/')
          return
        }
      }

      // Fallback: check if already signed in
      const { data } = await supabase.auth.getSession()
      if (data.session) {
        navigate('/')
      } else {
        navigate('/login')
      }
    }

    handleCallback()
  }, [navigate])

  return (
    <div className="min-h-screen flex items-center justify-center bg-bg-primary">
      <div className="text-primary">Completing sign in...</div>
    </div>
  )
}
