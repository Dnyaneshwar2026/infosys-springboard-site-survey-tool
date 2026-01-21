const BASE_URL = "http://localhost:8080/api";

export const getSpaces = async () => {
  const res = await fetch(`${BASE_URL}/spaces`);
  return res.json();
};

export const getTemplates = async () => {
  const res = await fetch(`${BASE_URL}/checklist-templates`);
  return res.json();
};

export const getQuestionsByTemplate = async (templateId) => {
  const res = await fetch(
    `${BASE_URL}/checklist-questions/template/${templateId}`
  );
  return res.json();
};

export const submitResponse = async (spaceId, questionId, answer) => {
  const res = await fetch(
    `${BASE_URL}/checklist-responses/space/${spaceId}/question/${questionId}`,
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ answer }),
    }
  );
  return res.json();
};
