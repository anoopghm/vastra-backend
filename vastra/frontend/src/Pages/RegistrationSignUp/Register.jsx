import Placeholder from "../../components/Placeholder";

export default function Register({ onBack }) {
  return (
    <div>
      <h2 className="text-xl font-semibold text-center mb-4">
        Register
      </h2>

      <div className="space-y-4">
        <Placeholder title="Enter your full name" />
        <Placeholder title="Enter your email" />
        <Placeholder title="Password" type="password" />

        <button className="fullButton">
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
