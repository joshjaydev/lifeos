import { NavLink, Outlet } from 'react-router-dom'
import { useAuth } from '../contexts/AuthContext'
import logo from '../assets/lifeOS-logo.png'

export default function Layout() {
  const { signOut, user } = useAuth()

  const navItems = [
    { to: '/time', label: 'Time' },
    { to: '/notes', label: 'Notes' },
    { to: '/values', label: 'Values' },
    { to: '/actions', label: 'Actions' },
  ]

  return (
    <div className="min-h-screen flex flex-col">
      <header className="bg-bg-secondary border-b border-gray-700 px-4 py-3">
        <div className="max-w-7xl mx-auto flex items-center justify-between">
          <div className="flex items-center gap-8">
            <div className="w-32 flex justify-center">
              <img src={logo} alt="LifeOS" className="h-10" />
            </div>
            <nav className="flex gap-1">
              {navItems.map((item) => (
                <NavLink
                  key={item.to}
                  to={item.to}
                  className={({ isActive }) =>
                    `px-4 py-2 rounded transition-colors ${
                      isActive
                        ? 'bg-primary text-black font-semibold'
                        : 'text-gray-300 hover:bg-bg-tertiary'
                    }`
                  }
                >
                  {item.label}
                </NavLink>
              ))}
            </nav>
          </div>
          <div className="flex items-center gap-4">
            <span className="text-sm text-gray-400">{user?.email}</span>
            <button
              onClick={signOut}
              className="text-sm text-gray-400 hover:text-white"
            >
              Sign Out
            </button>
          </div>
        </div>
      </header>
      <main className="flex-1 p-4">
        <div className="max-w-7xl mx-auto">
          <Outlet />
        </div>
      </main>
    </div>
  )
}
