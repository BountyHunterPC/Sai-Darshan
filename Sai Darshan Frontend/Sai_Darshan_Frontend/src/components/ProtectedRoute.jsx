import { Navigate } from "react-router"
import { useAuth } from "../providers/AuthProvider"

export default function ProtectedRoute(props) {
	const { user, loading } = useAuth()
	
	if (loading) {
		return <div className="min-h-screen flex items-center justify-center">Loading...</div>
	}
	
	if (!user) {
		return <Navigate to="/login" />
	}
	
	return props.children
}