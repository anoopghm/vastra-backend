import { useState } from "react";
import Placeholder from "../../components/Placeholder";

export default function Register({ onBack }) {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: ""
  });

  const [errors, setErrors] = useState({});

  const handleChange = (field, value) => {
    setForm({ ...form, [field]: value });
  };

  const handleSubmit = () => {
    let newErrors = {};

    if (!form.name.trim()) {
      newErrors.name = "Name cannot be blank";
    }

    if (!form.email.trim()) {
      newErrors.email = "Email cannot be blank";
    }

    if (!form.password.trim()) {
      newErrors.password = "Password cannot be blank";
    }

    setErrors(newErrors);

    if (Object.keys(newErrors).length > 0) return;

    // 🔥 call register API here
    
    console.log("Valid form:", form);
  };

  return (
    <div>
      <h2 className="text-xl font-semibold text-center mb-4">
        Register
      </h2>

      <div className="space-y-4">
        <Placeholder
          title="Enter your full name"
          value={form.name}
          onChange={(e) => handleChange("name", e.target.value)}
        />
        {errors.name && <p className="text-red-500 text-sm">{errors.name}</p>}

        <Placeholder
          title="Enter your email"
          value={form.email}
          onChange={(e) => handleChange("email", e.target.value)}
        />
        {errors.email && <p className="text-red-500 text-sm">{errors.email}</p>}

        <Placeholder
          title="Password"
          type="password"
          value={form.password}
          onChange={(e) => handleChange("password", e.target.value)}
        />
        {errors.password && (
          <p className="text-red-500 text-sm">{errors.password}</p>
        )}

        <button className="fullButton" onClick={handleSubmit}>
          Register
        </button>
      </div>

      {/* Already registered */}
      <button
        onClick={onBack}
        className="mt-4 text-blue-500 text-m block mx-auto hover:text-blue-700"
      >
        Already registered? Login
      </button>
    </div>
  );
}
