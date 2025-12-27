import { useState } from "react";
import Placeholder from "../../components/Placeholder";
import googleLogo from "../../assets/googleLogo.png"
import Register from "./Register";

export default function Login({ isOpen, onClose }) {
  if (!isOpen) return null;

  const [login, setLogin] = useState(true);

  return (
    <div className="fixed inset-0 bg-black/40 backdrop-blur-sm z-50 flex items-center justify-center">
      <div className="bg-white w-96 rounded-lg shadow-lg p-6 relative">
        <button
          onClick={onClose}
          className="absolute top-3 right-3 text-gray-500 hover:text-red-500 text-xl"
        >
          ✕
        </button>

        {login ? (
          <>
            <h2 className="text-xl font-semibold text-center mb-4">
              Login
            </h2>

            <div className="space-y-4">
              <Placeholder title="Email" />
              <Placeholder title="Password" type="password" />
              <button className="fullButton">
                Login
              </button>
            </div>

            <button
              onClick={() => setLogin(false)}
              className="mt-4 text-blue-600"
            >
             Not Registered yet? Register here
            </button>
          </>
        ) : (
          <Register onBack={() => setLogin(true)} />
        )}
        <button className="googleLogo">
               <img 
               src = {googleLogo}
               alt = "Google"
               className="w-5 h-5"
               />
                <span className="flex-1 text-center">
                  Continue with Google
               </span>
            </button>
      </div>
    </div>
  );
}
