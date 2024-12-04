import React, { useEffect, useState } from 'react';
import './UserDashboard.css';
import { useNavigate } from 'react-router-dom';
import { Button, Form, Navbar, Table } from 'react-bootstrap';
import axios from 'axios';
import { message } from 'antd';
import { FaEdit, FaTrash } from 'react-icons/fa';
import EditTask from './EditTask';
import AddTask from './AddTask';

const UserDashboard = () => {
  const nav=useNavigate()
  const [tasks, setTasks] = useState([]);
  const [filteredTasks, setFilteredTasks] = useState([]); 
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedTask, setSelectedTask] = useState(null);
  const [refresh, setRefresh] = useState(false);
  const [isAddModalOpen, setIsAddModalOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const token=localStorage.getItem('token')
  
  useEffect(() => {
    if (token) {
      axios
        .get(`http://localhost:8080/api/task/getAllTasks`, {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          setTasks(response.data);
          setFilteredTasks(response.data);
        })
        .catch((err) => {
          console.error("Error fetching tasks:", err);
        });
    }
  }, [refresh]);

  const handleEdit = (task) => {
    setSelectedTask(task);
    setIsEditModalOpen(true);
  };

  const handleSearch = (e) => {
    const term = e.target.value.toLowerCase();
    setSearchTerm(term);
    const filtered = tasks.filter(
      (task) =>
        task.title.toLowerCase().includes(term) ||
      task.description.toLowerCase().includes(term) ||
      task.priority.toLowerCase().includes(term) ||
      task.status.toLowerCase().includes(term)
    );
    setFilteredTasks(filtered);
  };

  const handlelogout=()=>{

    localStorage.removeItem("token")
    localStorage.removeItem("userid")
    localStorage.removeItem("role")
    nav("/")
    
  }

  return (
    <div className="user-dashboard">

      <div className="flex-grow-1">
      <Navbar bg="white" className="shadow-sm header px-3">
      <Navbar.Brand  className="text-primary fw-bold">Task Management</Navbar.Brand>
      <Navbar.Collapse className="justify-content-end">
        <Navbar.Text className="me-3">
          <div>USER</div>
        </Navbar.Text>
        <Button onClick={handlelogout} variant="outline-danger" size="sm">
          Logout
        </Button>
      </Navbar.Collapse>
    </Navbar>
        
    <div className="view-alltasks">
      <div className="add-task">
        <h4>All tasks</h4>
        <Button
          variant="primary"
          onClick={() => setIsAddModalOpen(true)}
          className="add-task-btn"
        >
          Add task
        </Button>
      </div>

      <div className="search-container">
        <Form.Group style={{width:'400px'}} >
          <Form.Control
            type="text"
            placeholder="Search by title, description,priority, or status"
            value={searchTerm}
            onChange={handleSearch}
          />
        </Form.Group>
        <br/>
      </div>

      {filteredTasks.length > 0 ? (
        <Table striped bordered hover responsive>
          <thead>
            <tr>
              <th><b>task ID</b></th>
              <th><b>Title</b></th>
              <th><b>Description</b></th>
              <th><b>Due Date</b></th>
              <th><b>Priority</b></th>
              <th><b>Status</b></th>
              <th><b>User Actions</b></th>
            </tr>
          </thead>
          <tbody>
            {filteredTasks.map((task) => (
              <tr key={task.taskId}>
                <td>
                  T000{task.taskId}
                </td>
                <td>{task.title}</td>
                <td>{task.description}</td>
                <td>{task.dueDate}</td>
                <td>{task.priority}</td>
                <td>{task.status}</td>
                <td>
                  <span
                    style={{ cursor: "pointer", color: "#17a2b8", marginRight: "10px" }}
                    onClick={() => handleEdit(task)}
                  >
                    <FaEdit size={18} title="Edit task" />
                  </span>
                  </td>
              </tr>
            ))}
          </tbody>
        </Table>
      ) : (
        <p>No tasks found!</p>
      )}

      {isAddModalOpen && (
        <AddTask
          show={isAddModalOpen}
          handleClose={() => setIsAddModalOpen(false)}
          setRefresh={() => setRefresh((prev) => !prev)}
        />
      )}

      {isEditModalOpen && selectedTask && (
        <EditTask
          show={isEditModalOpen}
          handleClose={() => setIsEditModalOpen(false)}
          setRefresh={() => setRefresh((prev) => !prev)}
          task={selectedTask}
        />
      )}
    </div>

      </div>
    </div>
  );
};

export default UserDashboard;
