import React, { useState } from 'react';

const FloorPlanLabeler = ({ floorPlanUrl }) => {
  const [labels, setLabels] = useState([]);
  const labelOptions = ['Kitchen', 'Bedroom', 'Bathroom', 'Living Room'];
  
  const addLabel = (e) => {
    const rect = e.currentTarget.getBoundingClientRect();
    const x = ((e.clientX - rect.left) / rect.width) * 100;
    const y = ((e.clientY - rect.top) / rect.height) * 100;
    
    const newLabel = {
      id: Date.now(),
      text: labelOptions[0],
      x, y, width: 15, height: 10
    };
    
    setLabels([...labels, newLabel]);
  };
  
  return (
    <div style={{ padding: '20px', border: '1px solid #ccc', margin: '20px 0' }}>
      <h3>📐 Floor Plan Labeler</h3>
      {floorPlanUrl ? (
        <div style={{ position: 'relative', display: 'inline-block' }} onClick={addLabel}>
          <img src={floorPlanUrl} alt="Floor Plan" style={{ maxWidth: '500px', height: 'auto' }} />
          {labels.map(label => (
            <div key={label.id} style={{
              position: 'absolute',
              left: `${label.x}%`,
              top: `${label.y}%`,
              width: `${label.width}%`,
              height: `${label.height}%`,
              background: 'rgba(255,0,0,0.5)',
              color: 'white',
              padding: '2px 5px',
              fontSize: '12px'
            }}>
              {label.text}
            </div>
          ))}
        </div>
      ) : (
        <p>No floor plan selected</p>
      )}
    </div>
  );
};

export default FloorPlanLabeler;
