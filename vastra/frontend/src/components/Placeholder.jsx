import { useState } from "react";

export default function Placeholder({ title, type = "text" }) {
  const [active, setActive] = useState(false);

  return (
    <fieldset
      className={`
        w-full
        border
        rounded
        px-3
        py-2
        transition-colors
        ${active ? "border-blue-500" : "border-gray-400"}
      `}
    >
      <legend
        className={`
          px-2
          transition-all
          duration-200
          ${active
            ? "text-sm text-blue-500"
            : "text-base text-gray-400"}
        `}
      >
        {active ? title : ""}
      </legend>

      <input
        type={type}
        className="w-full bg-transparent outline-none text-black"
        placeholder={!active ? title : ""}
        onFocus={() => setActive(true)}
        onBlur={(e) => setActive(e.target.value.length > 0)}
      />
    </fieldset>
  );
}
