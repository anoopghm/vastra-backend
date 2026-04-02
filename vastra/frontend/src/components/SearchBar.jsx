import { useState } from "react";
import { MdSearch, MdClose } from "react-icons/md";

export default function SearchBar() {
  const [search, setSearch] = useState("");
  const [isFocused, setIsFocused] = useState(false);

  const handleSearch = (e) => {
    setSearch(e.target.value);
  };

  const handleClear = () => {
    setSearch("");
  };

  return (
    <div className="relative w-full">
      <div
        className={`
          flex items-center gap-2 px-4 py-2.5 rounded-full border transition-all
          ${
            isFocused
              ? "border-[#2a4a66] bg-white shadow-md"
              : "border-gray-300 bg-gray-50 hover:border-gray-400"
          }
        `}
      >
        <MdSearch className={`text-xl ${isFocused ? "text-[#2a4a66]" : "text-gray-500"}`} />
        <input
          type="text"
          placeholder="Search..."
          value={search}
          onChange={handleSearch}
          onFocus={() => setIsFocused(true)}
          onBlur={() => setIsFocused(false)}
          className="flex-1 bg-transparent outline-none text-sm placeholder-gray-400"
        />
        {search && (
          <button
            onClick={handleClear}
            className="text-gray-500 hover:text-gray-700 transition"
            aria-label="Clear search"
          >
            <MdClose size={18} />
          </button>
        )}
      </div>
    </div>
  );
}
