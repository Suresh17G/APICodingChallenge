import React, { useState, useEffect } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import axios from "axios";
import { message } from "antd";

const EditTask = ({ show, handleClose, setRefresh, task }) => {
    const token=localStorage.getItem('token')

  const [formData, setFormData] = useState(task);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSave = () => {
    if (token) {
      axios
        .put(`http://localhost:8080/api/task/updateTask/${task.taskId}`, formData, {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        })
        .then((response) => {
          message.success("Task edited Successfully!");
          setRefresh(prev => !prev);
          handleClose();
        })
        .catch((err) => {
          console.error("Error editing Task:", err);
          message.error("Error editing Task");
        });
    }
  };

  const priorityOptions = [
    { key: 'low', text: 'Low', value: 'LOW' },
    { key: 'medium', text: 'Medium', value: 'MEDIUM' },
    { key: 'high', text: 'High', value: 'HIGH' },
  ];

  const statusOptions = [
    { key: 'Pending', text: 'Pending', value: 'PENDING' },
    { key: 'inprogress', text: 'In Progress', value: 'INPROGRESS' },
    { key: 'Completed', text: 'Completed', value: 'COMPLETED' },
  ];

  return (
    <Modal show={show} onHide={handleClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>Edit Task Details</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
        <Form.Group className="mb-3" controlId="title">
            <Form.Label>Title</Form.Label>
            <Form.Control
              type="text"
              name="title"
              value={formData.title}
              onChange={handleChange}
              placeholder="Enter task title"
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="description">
            <Form.Label>Description</Form.Label>
            <Form.Control
              type="text"
              name="description"
              value={formData.description}
              onChange={handleChange}
              placeholder="Enter Task description"
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="dueDate">
            <Form.Label>Due Date</Form.Label>
            <Form.Control
              type="date"
              name="dueDate"
              value={formData.dueDate}
              onChange={handleChange}
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="priority">
            <Form.Label>Priority</Form.Label>
            <Form.Control
              as="select"
              name="priority"
              value={formData.priority}
              onChange={handleChange}
            >
              <option value="">Select Priority</option>
              {priorityOptions.map((option) => (
                <option key={option.key} value={option.value}>
                  {option.text}
                </option>
              ))}
            </Form.Control>
          </Form.Group>
          
          <Form.Group className="mb-3" controlId="status">
            <Form.Label>Status</Form.Label>
            <Form.Control
              as="select"
              name="status"
              value={formData.status}
              onChange={handleChange}
            >
              <option value="">Select Status</option>
              {statusOptions.map((option) => (
                <option key={option.key} value={option.value}>
                  {option.text}
                </option>
              ))}
            </Form.Control>
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Cancel
        </Button>
        <Button variant="primary" onClick={handleSave}>
          Edit
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default EditTask;
