import { useState } from "react";
import Placeholder from "../Placeholder";
import Login from "../../Pages/RegistrationSignUp/Login.jsx";
import logo from "../../assets/logo.png";

export default function Navbar() {
  const [openLogin, setOpenLogin] = useState(false);

  return (
    <>
      <nav className="bg-white border-b-4 border-red-500 shadow-md sticky top-0 z-40">
        <div className="max-w-7xl mx-auto px-6 py-4 flex items-center justify-between">
          
          {/* Logo */}
          <div className="flex items-center">
            <img
              src={logo}
              alt="Hitachi Logo"
              className="h-9 w-10 w-auto object-contain"
            />
          </div>

          {/* Search */}
          <div className="w-1/3">
            <Placeholder title="search" />
          </div>

          {/* Login Button */}
          <button 
          className="smallButton"
          onClick={() => setOpenLogin(true)}
          >
          Login
          </button>
        </div>
      </nav>

      {/* Login Popup */}
      <Login
        isOpen={openLogin}
        onClose={() => setOpenLogin(false)}
      />
    </>
  );
}
