import insta from "../assets/insta.jpeg";

export default function Footer() {
  return (
    <div>
      <a
        href="https://www.instagram.com/hi_assmiclothing/"
        target="_blank"
        rel="noopener noreferrer"
      >
        <img
        className="h-10 w-10 px-1 py-1 rounded-full"
          src={insta}
          alt="Instagram"
        />
      </a>
    </div>
  );
}
