import { useState } from "react";
import SpacesList from "./pages/SpacesList";
import TemplatesList from "./pages/TemplatesList";
import SurveyForm from "./pages/SurveyForm";

function App() {
  const [space, setSpace] = useState(null);
  const [template, setTemplate] = useState(null);

  if (!space) return <SpacesList onSelect={setSpace} />;
  if (!template) return <TemplatesList onSelect={setTemplate} />;

  return <SurveyForm space={space} template={template} />;
}

export default App;
