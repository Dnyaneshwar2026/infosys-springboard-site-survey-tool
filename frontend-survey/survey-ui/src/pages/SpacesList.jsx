import { useEffect, useState } from "react";
import { getSpaces } from "../api/api";

function SpacesList({ onSelect }) {
  const [spaces, setSpaces] = useState([]);

  useEffect(() => {
    getSpaces().then((data) => {
      console.log("RAW DATA FROM BACKEND:", data);

      const validSpaces = data.filter(
        (s) => s.name && s.name.trim() !== ""
      );

      console.log("AFTER FILTER:", validSpaces);

      setSpaces(validSpaces);
    }).catch(err => {
      console.error("API ERROR:", err);
    });
  }, []);

  return (
    <div>
      <h2>Select Space</h2>

      <p>Total spaces after filter: {spaces.length}</p>

      {spaces.map((space) => (
        <button
          key={space.id}
          onClick={() => onSelect(space)}
          style={{ display: "block", margin: "8px 0" }}
        >
          {space.name}
        </button>
      ))}
    </div>
  );
}

export default SpacesList;
