import { useState } from "react";
import SearchBar from "../SearchBar";
import Login from "../../Pages/RegistrationSignUp/Login.jsx";
import logo from "../../assets/logo.png";
import search from "../../assets/search.png";
import { useAuth } from "../../context/AuthContext";
import { FaShoppingCart } from "react-icons/fa";

export default function Navbar() {
  const [openLogin, setOpenLogin] = useState(false);
  const [menuOpen, setMenuOpen] = useState(false);
  const [searchOpen, setSearchOpen] = useState(false);

  const { user, logout, loading } = useAuth();

  const handleLogout = async () => {
    await logout();
    setMenuOpen(false);
  };

  if (loading) {
    return (
      <nav className="bg-[#F5E6D3] sticky top-0 z-40 py-2">
        <div className="bg-white max-w-7xl mx-auto px-4 py-3 flex justify-between items-center">
          <img src={logo} alt="logo" className="h-8 w-auto" />
          <div className="text-[#2a4a66] text-sm">Loading...</div>
        </div>
      </nav>
    );
  }
 
  return (
    <>
      {/*  FULL SCREEN SEARCH */}
      {searchOpen && (
      <div
  className={`fixed top-0 right-0 h-full w-full bg-white z-50 transform transition-transform duration-[1500ms] ease-in-out ${
    searchOpen ? "translate-x-0" : "translate-x-full"
  }`}
>
  <div className="flex items-center gap-3 p-4 border-b">

    {/* Back Button */}

    {/* Search Input */}
    <div className="flex-1">
      <SearchBar autoFocus />
    </div>
    <button
      onClick={() => setSearchOpen(false)}
      className="text-sm font-medium transition"
    >
      Cancel
    </button>
  </div>
</div>
      )}

      {/*  NAVBAR */}
      {!searchOpen && (
        <nav className="bg-[#f7f7e9] sticky top-0 z-40 py-3">
          <div className="bg-white">
            <div className="max-w-7xl mx-auto px-3 md:px-6 py-2 md:py-4 flex items-center justify-between gap-2 md:gap-6">

              {/* LEFT */}
              <div className="flex items-center gap-2">
                <div
                  className="flex flex-col cursor-pointer md:hidden"
                  onClick={() => setMenuOpen(!menuOpen)}
                >
                  <span className="h-0.5 w-5 bg-black mb-1 rounded"></span>
                  <span className="h-0.5 w-5 bg-black mb-1 rounded"></span>
                  <span className="h-0.5 w-5 bg-black rounded"></span>
                </div>

                <img src={logo} alt="logo" className="h-8 md:h-10 w-auto" />
              </div>

              {/* SEARCH ICON */}
             
              {/* RIGHT */}
              <div className="flex items-center gap-5 md:gap-4">
                    <button onClick={() => setSearchOpen(true)}>
                <img src={search} alt="search" className="h-5 w-5" />
              </button>

                <button className="p-1 md:p-2">
                  <FaShoppingCart style={{ fontSize: "20px", color: "#2a4a66" }} />
                </button>

                <div className="hidden md:flex gap-2">
                  {!user ? (
                    <button
                      className="bg-[#2a4a66] text-white px-4 py-2 rounded-lg"
                      onClick={() => setOpenLogin(true)}
                    >
                      Login
                    </button>
                  ) : (
                    <button
                      className="bg-[#2a4a66] text-white px-4 py-2 rounded-lg"
                      onClick={handleLogout}
                    >
                      Logout
                    </button>
                  )}
                </div>
              </div>
            </div>
          </div>
        </nav>
      )}

      {/* LOGIN MODAL */}
      {!user && (
        <Login
          isOpen={openLogin}
          onClose={() => setOpenLogin(false)}
        />
      )}

      {/*  OVERLAY */}
      {menuOpen && (
        <div
          className="fixed inset-0 bg-black/40 z-40"
          onClick={() => setMenuOpen(false)}
        />
      )}

      {/* SIDEBAR */}
      <div
        className={`fixed top-0 left-0 h-full w-[80%] bg-white z-50 shadow-lg transform transition-transform duration-300 ${
          menuOpen ? "translate-x-0" : "-translate-x-full"
        }`}
      >
        <div className="p-6 flex flex-col h-full">

          {/* HEADER */}
          <div className="flex justify-between items-center mb-6">
            <img src={logo} alt="logo" className="h-8" />
            <button onClick={() => setMenuOpen(false)}>✕</button>
          </div>

          {/* MENU */}
          <nav className="flex flex-col gap-2">
            <button className="text-left py-3 px-3 hover:bg-gray-100">My Orders</button>
            <button className="text-left py-3 px-3 hover:bg-gray-100">My Profile</button>
            <button className="text-left py-3 px-3 hover:bg-gray-100">Want to Seller?</button>
          </nav>

          {/* AUTH */}
          <div className="mt-auto pt-6 border-t">
            {!user ? (
              <button
                className="w-full bg-[#2a4a66] text-white py-3 rounded-lg"
                onClick={() => {
                  setOpenLogin(true);
                  setMenuOpen(false);
                }}
              >
                Login
              </button>
            ) : (
              <button
                className="w-full bg-[#2a4a66] text-white py-3 rounded-lg"
                onClick={handleLogout}
              >
                Logout
              </button>
            )}
          </div>
        </div>
      </div>
    </>
  );
}