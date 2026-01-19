import React, { useState, useEffect } from 'react';

const ChecklistForm = ({ checklists }) => {
  const [responses, setResponses] = useState({});
  
  // 🔧 FIX: Handle non-array checklists (null, object, undefined)
  const safeChecklists = Array.isArray(checklists) ? checklists : [];
  
  useEffect(() => {
    const draft = localStorage.getItem('surveyDraft');
    if (draft) setResponses(JSON.parse(draft));
  }, []);
  
  const handleChange = (itemId, value) => {
    setResponses({ ...responses, [itemId]: value });
    localStorage.setItem('surveyDraft', JSON.stringify({ ...responses, [itemId]: value }));
  };
  
  const submit = () => {
    alert(`✅ Survey submitted! ${Object.keys(responses).length} responses saved`);
    console.log('Responses:', responses);
  };
  
  // 🔧 FIX: Safe rendering - no more .map errors
  if (!safeChecklists.length) {
    return (
      <div style={{ padding: '20px', border: '1px solid #ccc', background: '#f0f8ff' }}>
        <h3>📋 Checklists Loading...</h3>
        <p>• Spring Boot running? (localhost:8080)</p>
        <p>• MySQL data inserted? (4 checklists, 16 questions)</p>
        <p><strong>Console:</strong> Check browser F12 → Network tab</p>
      </div>
    );
  }
  
  return (
    <div style={{ padding: '20px', border: '1px solid #ccc', margin: '20px 0' }}>
      <h3>📋 Checklists ({safeChecklists.length}) - {Object.keys(responses).length} responses</h3>
      
      {safeChecklists.map((checklist, clIndex) => (
        <div key={clIndex} style={{ marginBottom: '20px', padding: '15px', background: '#f5f5f5' }}>
          <h4>{checklist.name || `Checklist ${clIndex + 1}`}</h4>
          
          {/* 🔧 FIX: Safe items array */}
          {Array.isArray(checklist.items) ? (
            checklist.items.map((item, itemIndex) => (
              <div key={itemIndex} style={{ margin: '10px 0', padding: '10px', borderBottom: '1px solid #eee' }}>
                <label style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>
                  {item.question || `Question ${itemIndex + 1}`}
                </label>
                
                {item.type === 'checkbox' ? (
                  <input 
                    type="checkbox" 
                    checked={responses[item.id] || false}
                    onChange={(e) => handleChange(item.id || itemIndex, e.target.checked)}
                  />
                ) : item.type === 'radio' ? (
                  <select 
                    onChange={(e) => handleChange(item.id || itemIndex, e.target.value)}
                    style={{ padding: '5px', marginLeft: '10px' }}
                  >
                    <option value="">Select...</option>
                    <option value="Yes">Yes</option>
                    <option value="No">No</option>
                    <option value="N/A">N/A</option>
                  </select>
                ) : (
                  <input 
                    type="text"
                    placeholder="Enter response..."
                    value={responses[item.id] || ''}
                    onChange={(e) => handleChange(item.id || itemIndex, e.target.value)}
                    style={{ padding: '5px', marginLeft: '10px', width: '300px' }}
                  />
                )}
              </div>
            ))
          ) : (
            <p>No items in this checklist</p>
          )}
        </div>
      ))}
      
      <button 
        onClick={submit} 
        style={{ 
          padding: '12px 24px', 
          background: '#28a745', 
          color: 'white', 
          border: 'none', 
          borderRadius: '6px',
          fontSize: '16px',
          cursor: 'pointer'
        }}
      >
        ✅ Submit Survey ({Object.keys(responses).length} responses)
      </button>
    </div>
  );
};

export default ChecklistForm;
