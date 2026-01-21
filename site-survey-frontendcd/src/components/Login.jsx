const Login = ({ onLogin }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  
  const handleLogin = () => {
    // Simple auth - store in localStorage
    localStorage.setItem('user', JSON.stringify({email, role: 'surveyor'}));
    onLogin({email, role: 'surveyor'});
  };
  
  return (
    <div className="login">
      <h2>🔐 Login</h2>
      <input placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};
