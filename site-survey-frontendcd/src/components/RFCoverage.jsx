const RFCoverage = ({ buildingId }) => {
  const [rfData, setRfData] = useState([]);
  
  useEffect(() => {
    // Mock Vistumbler/Kismet data
    setRfData([
      {ap: 'AP-01', signal: -45, location: 'Kitchen'},
      {ap: 'AP-02', signal: -65, location: 'Bedroom'},
    ]);
  }, []);
  
  return (
    <div className="rf-coverage">
      <h3>📡 RF Coverage Overlay</h3>
      <table>
        <thead><tr><th>AP</th><th>Signal</th><th>Location</th></tr></thead>
        <tbody>{rfData.map(d => (
          <tr key={d.ap}><td>{d.ap}</td><td>{d.signal}dBm</td><td>{d.location}</td></tr>
        ))}</tbody>
      </table>
    </div>
  );
};
