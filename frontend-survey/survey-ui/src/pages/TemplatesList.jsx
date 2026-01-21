import { useEffect, useState } from "react";
import { getTemplates } from "../api/api";

function TemplatesList({ onSelect }) {
  const [templates, setTemplates] = useState([]);

  useEffect(() => {
    getTemplates().then(setTemplates);
  }, []);

  return (
    <div>
      <h2>Select Checklist</h2>
      {templates.map(t => (
        <button key={t.id} onClick={() => onSelect(t)}>
          {t.name}
        </button>
      ))}
    </div>
  );
}

export default TemplatesList;
