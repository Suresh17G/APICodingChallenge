import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Typography, Input } from 'antd';
import './Login.css';

const { Title } = Typography;

const Login = () => {
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = (e) => {
    e.preventDefault();
    const logindata = {username,password};

    axios
      .post('http://localhost:8080/api/login', logindata)
      .then((res) => {
        localStorage.setItem('token', res.data.jwt);
        localStorage.setItem('userid', res.data.userid);
        localStorage.setItem('role', res.data.role);
        let role=localStorage.getItem('role');
        if (role === 'USER') {
          navigate('/user/dashboard');
        } else if (role === 'ADMIN') {
          navigate('/admin/dashboard');
        }
      })
      .catch(() => {
        alert('Login Failed');
      });
  };

  return (
    <div className="mycontainer">
      <h2 className="app-title">Task Management</h2>
      <div className="login-container">
        <div className="login-card">
          <Title level={3} className="login-title">
            Login
          </Title>
          <Form className="login-form">
            <Form.Group controlId="username">
              <Input
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </Form.Group>
            <Form.Group controlId="password" className="mt-3">
              <Input.Password
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </Form.Group>
            <Button
              type="primary"
              className="login-form-button mt-4"
              onClick={handleLogin}
            >
              Log in
            </Button>
          </Form>
        </div>
      </div>
    </div>
  );
};

export default Login;
