import React, { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import axios from "axios";
import { message } from "antd";

const AddUser = ({ show, handleClose, setRefresh }) => {
    
const token=localStorage.getItem('token')

  const [formData, setFormData] = useState({
    username: "",
    password: ""
  });
  const [repassword, setrepassword] = useState('')

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSave = () => {
    if(formData.password!==repassword){
      message.error("Password must be same!!")
    }else{
    if (token) {
      axios
        .post(`http://localhost:8080/api/addUser`, formData, {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json"
            }})
        .then((response) => {
          message.success("User added Successfully!");
          setRefresh(prev => !prev); 
          handleClose(); 
        })
        .catch((err) => {
          console.error("Error adding User:", err);
          message.error("Error adding User");
        });
    }}
  };


  return (
    <Modal show={show} onHide={handleClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>Add New User</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="username">
            <Form.Label>Username</Form.Label>
            <Form.Control
              type="text"
              name="username"
              value={formData.username}
              onChange={handleChange}
              placeholder="Enter Username"
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="password">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              placeholder="Enter Password"
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="repassword">
            <Form.Label>Re-enter Password</Form.Label>
            <Form.Control
              type="password"
              name="password"
              onChange={(e)=>setrepassword(e.target.value)}
              placeholder="Enter same Password again"
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Cancel
        </Button>
        <Button variant="primary" onClick={handleSave}>
          Add User
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AddUser;
