// DeleteConfirmation.js
import React from 'react';
import { Popconfirm } from 'antd';

const DeleteConfirmation = ({ onConfirm, onCancel }) => (
  <Popconfirm
    title="Delete the Task"
    description="Are you sure you want to delete this task?"
    onConfirm={onConfirm}
    onCancel={onCancel}
    okText="Yes"
    cancelText="No"
    okButtonProps={{
        style: {
          width: '80px',  
          marginRight: '10px',
        },
      }}
      cancelButtonProps={{
        style: {
          width: '80px', 
          marginLeft: '10px', 
        },
      }}
  ><br/>
  </Popconfirm>
);

export default DeleteConfirmation;
