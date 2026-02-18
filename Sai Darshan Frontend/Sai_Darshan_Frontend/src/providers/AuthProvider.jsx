import { useContext, useEffect } from "react"
import { useState } from "react"
import { createContext } from "react"

export const AuthContext = createContext({})

function AuthProvider(props) {
	const [user, setUser] = useState(null)
	const [loading, setLoading] = useState(true)
	
	useEffect(() => {
		const storedUser = localStorage.getItem('user')
		if (storedUser) {
			setUser(JSON.parse(storedUser))
		}
		setLoading(false)
	}, [])
	
	return <AuthContext.Provider value={{user, setUser, loading}}>
		{props.children}
	</AuthContext.Provider>
}

export default AuthProvider

export function useAuth() {
	const auth = useContext(AuthContext)
	return auth
}