import { useState } from "react";
import Placeholder from "../../components/Placeholder";
import googleLogo from "../../assets/googleLogo.png";
import Register from "./Register";
import { useAuth } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";
import { API_URL } from "../../util/config";
import api from "../../api/axios";

export default function Login({ isOpen, onClose }) {
  if (!isOpen) return null;

  const { login: loginUser } = useAuth();
  const navigate = useNavigate();
  const [isLogin, setIsLogin] = useState(true);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async () => {
  setError("");
  try {
    await loginUser({ email, password });  
    navigate("/");
    onClose();
  } catch {
    setError("Invalid email or password");
  }
};

  const handleGoogleLogin = () => {
    window.location.href = `${API_URL}/oauth2/authorization/google`;
  };

  return (
    <div className="fixed inset-0 bg-black/40 backdrop-blur-sm z-50 flex items-center justify-center">
      <div className="bg-white w-96 rounded-lg shadow-lg p-6 relative">
        <button
          onClick={onClose}
          className="absolute top-3 right-3 text-gray-500 hover:text-red-500 text-xl"
        >
          ✕
        </button>

        {isLogin ? (
          <>
            <h2 className="text-xl font-semibold text-center mb-4">Login</h2>

            <div className="space-y-4">
              <Placeholder
                title="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />

              <Placeholder
                title="Password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />

              {error && (
                <p className="text-red-500 text-sm text-center">{error}</p>
              )}
              </div>
              <div className="flex justify-center items-center">
  <div className="flex gap-2 bg-gray-200 p-2 rounded-full">
    
    <button
      className="bg-[#2a4a66] text-white py-2 px-6 rounded-full"
      onClick={handleLogin}
    >
      Login
    </button>

    <button
      onClick={() => setIsLogin(false)}
      className="bg-[#c4d8f2] py-2 px-6 rounded-full"
    >
      Register
    </button>

  </div>
</div>
          </>
        ) : (
          <Register onBack={() => setIsLogin(true)} />
        )}

        {/* ✅ GOOGLE LOGIN BUTTON */}
        <button className="googleLogo mt-4" onClick={handleGoogleLogin}>
          <img src={googleLogo} alt="Google" className="w-5 h-5" />
          <span className="flex-1 text-center">Continue with Google</span>
        </button>
      </div>
    </div>
  );
}
