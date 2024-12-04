import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './Components/Login';
import AdminDashboard from './Components/AdminDashboard';
import UserDashboard from './Components/UserDashboard';

const Routing = () => {
  return (
    <Routes>

      <Route path="/" element={<Login/>} />    
      <Route path="/user/dashboard" element={<UserDashboard/>} />    
      <Route path="/admin/dashboard" element={<AdminDashboard/>} />    

      
    </Routes>
  );
};

export default Routing;
