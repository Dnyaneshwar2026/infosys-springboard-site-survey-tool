import { useEffect, useState } from "react";
import { getQuestionsByTemplate, submitResponse } from "../api/api";

function SurveyForm({ space, template }) {
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});

  useEffect(() => {
    getQuestionsByTemplate(template.id).then(setQuestions);
  }, [template]);

  const submit = async () => {
    for (let q of questions) {
      await submitResponse(space.id, q.id, answers[q.id]);
    }
    alert("Survey submitted");
  };

  return (
    <div>
      <h3>{space.name} – Survey</h3>

      {questions.map(q => (
        <div key={q.id}>
          <label>{q.questionText}</label><br/>

          {q.answerType === "TEXT" &&
            <input onChange={e =>
              setAnswers({ ...answers, [q.id]: e.target.value })} />}

          {q.answerType === "NUMBER" &&
            <input type="number" onChange={e =>
              setAnswers({ ...answers, [q.id]: e.target.value })} />}

          {q.answerType === "BOOLEAN" &&
            <select onChange={e =>
              setAnswers({ ...answers, [q.id]: e.target.value })}>
              <option value="">Select</option>
              <option value="true">Yes</option>
              <option value="false">No</option>
            </select>}
        </div>
      ))}

      <button onClick={submit}>Submit</button>
    </div>
  );
}

export default SurveyForm;
