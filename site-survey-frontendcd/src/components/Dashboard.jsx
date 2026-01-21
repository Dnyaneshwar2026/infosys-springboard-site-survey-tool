const Dashboard = () => {
  const [stats, setStats] = useState([]);
  
  useEffect(() => {
    axios.get('http://localhost:8080/api/stats').then(res => setStats(res.data));
  }, []);
  
  return (
    <div className="dashboard">
      <h1>📊 Survey Dashboard</h1>
      <div className="stats-grid">
        {stats.map(stat => (
          <div key={stat.buildingId} className="stat-card">
            <h3>{stat.buildingName}</h3>
            <div>📋 {stat.totalChecklists} Checklists</div>
            <div>✅ {stat.completedChecklists} Completed</div>
            <div>{stat.completionPercentage}% Complete</div>
            <div className={`status ${stat.completionPercentage > 80 ? 'good' : 'warning'}`}>
              {stat.completionPercentage > 80 ? '🏆 Ready' : '⏳ In Progress'}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
