import React, { useState, useEffect } from 'react';
import FloorPlanLabeler from './components/FloorPlanLabeler';
import ChecklistForm from './components/ChecklistForm';
import axios from 'axios';
import './App.css';

function App() {
  const [buildings, setBuildings] = useState([]);
  const [selectedBuilding, setSelectedBuilding] = useState(null);
  const [checklists, setChecklists] = useState([]);
  
  const BASE_URL = 'http://localhost:8080/api';
  
  useEffect(() => {
    fetchBuildings();
  }, []);
  
  const fetchBuildings = async () => {
    try {
      const response = await axios.get(`${BASE_URL}/buildings`);
      setBuildings(response.data);
    } catch (error) {
      console.log('Backend not running yet');
    }
  };
  
  const fetchChecklists = async (buildingId) => {
    try {
      const response = await axios.get(`${BASE_URL}/checklists/building/${buildingId}`);
      setChecklists(response.data);
    } catch (error) {
      console.log('No checklists yet');
    }
  };
  
  return (
    <div className="App">
      <h1>🏠 Site Survey Tool - Milestone 3</h1>
      
      <div className="buildings-grid">
        {buildings.map(building => (
          <div key={building.id} className="building-card" 
               onClick={() => {
                 setSelectedBuilding(building);
                 fetchChecklists(building.id);
               }}>
            <h3>{building.name}</h3>
            <p>{building.address}</p>
            {building.floorPlanImage && (
              <img src={`${BASE_URL}/buildings/${building.id}/floorplan`} 
                   alt="Floor Plan" 
                   width="200" 
                   onError={(e) => e.target.style.display = 'none'} />
            )}
          </div>
        ))}
        {!buildings.length && (
          <p>👆 Click "npm start" in Spring Boot first, then refresh</p>
        )}
      </div>
      
      {selectedBuilding && (
        <div className="survey-section">
          <FloorPlanLabeler floorPlanUrl={`${BASE_URL}/buildings/${selectedBuilding.id}/floorplan`} />
          <ChecklistForm checklists={checklists} />
        </div>
      )}
    </div>
  );
}

export default App;
