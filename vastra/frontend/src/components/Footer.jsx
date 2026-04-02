import { FaInstagram, FaTiktok } from "react-icons/fa";

export default function Footer() {
  return (
    <footer className="bg-[#1a1a1a] text-white w-full border-t-2 border-[#2a4a66]">
      <div className="max-w-7xl mx-auto px-4 md:px-6 py-6 md:py-10">
        
        {/* Top Row: Brand + Links + Social */}
        <div className="grid grid-cols-3 md:grid-cols-5 gap-4 mb-6">
          
          {/* Brand */}
          <div className="col-span-1">
            <h3 className="text-lg font-bold text-[#2a4a66]">Hitachi</h3>
            <p className="text-gray-400 text-xs">Since 2022</p>
          </div>

          {/* Shop Links */}
          <div>
            <h4 className="font-semibold text-xs mb-2 text-white">Shop</h4>
            <ul className="space-y-1 text-xs text-gray-400">
              <li><a href="#" className="hover:text-[#2a4a66]">Men</a></li>
              <li><a href="#" className="hover:text-[#2a4a66]">Women</a></li>
            </ul>
          </div>

          {/* Info */}
          <div>
            <h4 className="font-semibold text-xs mb-2 text-white">Info</h4>
            <ul className="space-y-1 text-xs text-gray-400">
              <li><a href="#" className="hover:text-[#2a4a66]">About</a></li>
              <li><a href="#" className="hover:text-[#2a4a66]">Contact</a></li>
            </ul>
          </div>

          {/* Follow */}
          <div>
            <h4 className="font-semibold text-xs mb-2 text-white">Follow</h4>
            <div className="flex gap-2">
              <a href="https://www.instagram.com" target="_blank" rel="noopener noreferrer" aria-label="Instagram">
                <FaInstagram size={14} className="hover:text-[#2a4a66]" />
              </a>
              <a href="https://www.tiktok.com" target="_blank" rel="noopener noreferrer" aria-label="TikTok">
                <FaTiktok size={14} className="hover:text-[#2a4a66]" />
              </a>
            </div>
          </div>
        </div>

        {/* Bottom: Copyright */}
        <div className="border-t border-gray-700 pt-4 text-center text-xs text-gray-500">
          <p>&copy; 2026 Hitachi Clothing</p>
        </div>
      </div>
    </footer>
  );
}
