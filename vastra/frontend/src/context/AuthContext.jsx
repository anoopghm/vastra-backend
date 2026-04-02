import { createContext, useContext, useEffect, useState } from "react";
import api from "../api/axios";

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get("/me")              
      .then(res => setUser(res.data))
      .catch((error) => {
        // Silently handle 401 (unauthorized after logout)
        if (error.response?.status !== 401) {
          console.error("Auth check failed:", error);
        }
        setUser(null);
      })
      .finally(() => setLoading(false));
  }, []);

  const login = async (credentials) => {
  const res = await api.post("/auth/login", credentials);
  setUser(res.data);   // ✅ FIXED
  return res.data;
};

  const logout = async () => {
    try {
      await api.post("/auth/logout"); 
    } catch {}
    setUser(null);
    window.location.href = "/";
  };

  return (
    <AuthContext.Provider value={{ user, loading, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
