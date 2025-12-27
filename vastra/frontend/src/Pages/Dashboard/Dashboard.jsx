import Navbar from "../../components/Navbar/Navbar";
import background from "../../assets/background.jpg";
import Footer from "../../components/Footer";

export default function Dashboard() {
  return (
    <div
      className="min-h-screen bg-contain bg-center bg-no-repeat relative"
      style={{ backgroundImage: `url(${background})` }}
    >
      {/* Overlay */}
      <div className="absolute inset-0 bg-black/50"></div>

      {/* Content */}
      <div className="relative z-10 flex flex-col min-h-screen">
        <Navbar />

        {/* Hero Content */}
        <div className="flex flex-col items-center justify-center text-center flex-1">
          <h1 className="text-white text-4xl md:text-5xl font-bold tracking-wide opacity-0 animate-fadeUp">
            Style Knows No Gender
          </h1>

          <button className="mt-24 bg-red-600 text-white px-8 py-3 font-bold rounded-full hover:bg-gray-900 opacity-0 animate-fadeUpDelay">
            Shop Now
          </button>
        </div>

        {/* Footer */}
        <div className="flex justify-center pb-6">
          <Footer />
        </div>
      </div>
    </div>
  );
}
