import React, { useState, useEffect } from 'react';
import FloorPlanLabeler from './components/FloorPlanLabeler';
import ChecklistForm from './components/ChecklistForm';
import axios from 'axios';
import './App.css';

function App() {
  const [buildings, setBuildings] = useState([]);
  const [selectedBuilding, setSelectedBuilding] = useState(null);
  const [checklists, setChecklists] = useState([]);
  const [overviewStats, setOverviewStats] = useState(null);   // milestone 4: summary stats
  const [activePage, setActivePage] = useState('survey');     // 'survey' | 'dashboard' | 'rf'

  const BASE_URL = 'http://localhost:8080/api';

  useEffect(() => {
    fetchBuildings();
    fetchOverviewStats(); // load dashboard stats
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

  // Milestone 4: overall stats for dashboard
  const fetchOverviewStats = async () => {
    try {
      const response = await axios.get(`${BASE_URL}/stats/overview`);
      setOverviewStats(response.data);
    } catch (error) {
      console.log('No stats endpoint yet');
    }
  };

  // Milestone 4: download PDF report for a building
  const downloadPdfReport = async (buildingId) => {
    try {
      const response = await axios.get(
        `${BASE_URL}/reports/buildings/${buildingId}`,
        { responseType: 'blob' } // important for PDF
      );

      const blob = new Blob([response.data], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = `building-${buildingId}-report.pdf`;
      document.body.appendChild(a);
      a.click();
      a.remove();
      window.URL.revokeObjectURL(url);
    } catch (error) {
      console.error('Failed to download PDF', error);
    }
  };

  const renderNav = () => (
    <div className="top-nav">
      <button
        className={activePage === 'survey' ? 'active' : ''}
        onClick={() => setActivePage('survey')}
      >
        Survey
      </button>
      <button
        className={activePage === 'dashboard' ? 'active' : ''}
        onClick={() => setActivePage('dashboard')}
      >
        Dashboard
      </button>
      <button
        className={activePage === 'rf' ? 'active' : ''}
        onClick={() => setActivePage('rf')}
      >
        RF Coverage
      </button>
    </div>
  );

  const renderDashboard = () => {
    if (!overviewStats) {
      return <p>No stats available yet.</p>;
    }

    const {
      totalBuildings,
      totalChecklists,
      completedChecklists,
      completionRate
    } = overviewStats;

    return (
      <div className="dashboard">
        <h2>Summary Dashboard</h2>
        <div className="stats-cards">
          <div className="stat-card">
            <h3>Total Buildings</h3>
            <p>{totalBuildings}</p>
          </div>
          <div className="stat-card">
            <h3>Total Checklists</h3>
            <p>{totalChecklists}</p>
          </div>
          <div className="stat-card">
            <h3>Completed Checklists</h3>
            <p>{completedChecklists}</p>
          </div>
          <div className="stat-card">
            <h3>Completion Rate</h3>
            <p>{completionRate}%</p>
          </div>
        </div>
        {/* you can later add charts here */}
      </div>
    );
  };

  const renderRfPage = () => (
    <div className="rf-page">
      <h2>RF Tools & Coverage</h2>
      <p>Here you will integrate Vistumbler, Kismet and SPLAT! imports and map overlays.</p>
      {/* later: file uploads and map component go here */}
    </div>
  );

  const renderSurveyPage = () => (
    <>
      <h1>🏠 Site Survey Tool - Milestone 4</h1>

      <div className="buildings-grid">
        {buildings.map((building) => (
          <div
            key={building.id}
            className="building-card"
            onClick={() => {
              setSelectedBuilding(building);
              fetchChecklists(building.id);
            }}
          >
            <h3>{building.name}</h3>
            <p>{building.address}</p>

            {building.floorPlanImage && (
              <img
                src={`${BASE_URL}/buildings/${building.id}/floorplan`}
                alt="Floor Plan"
                width="200"
                onError={(e) => (e.target.style.display = 'none')}
              />
            )}

            {/* Milestone 4: button to download PDF report */}
            <button
              type="button"
              onClick={(e) => {
                e.stopPropagation(); // avoid selecting building when just downloading
                downloadPdfReport(building.id);
              }}
            >
              Download PDF Report
            </button>
          </div>
        ))}

        {!buildings.length && (
          <p>👆 Start Spring Boot backend, then refresh this page.</p>
        )}
      </div>

      {selectedBuilding && (
        <div className="survey-section">
          <FloorPlanLabeler
            floorPlanUrl={`${BASE_URL}/buildings/${selectedBuilding.id}/floorplan`}
          />
          <ChecklistForm checklists={checklists} />
        </div>
      )}
    </>
  );

  return (
    <div className="App">
      {renderNav()}

      {activePage === 'survey' && renderSurveyPage()}
      {activePage === 'dashboard' && renderDashboard()}
      {activePage === 'rf' && renderRfPage()}
    </div>
  );
}

export default App;
