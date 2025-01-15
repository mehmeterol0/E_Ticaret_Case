import React from 'react';
import { Modal, Button } from '@mui/material';

const CustomModal = ({ open, handleClose, message }) => {
  return (
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <div style={{
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
      }}>
        <h2 id="modal-modal-title">{message}</h2>
        <Button onClick={handleClose}>Close</Button>
      </div>
    </Modal>
  );
};

export default CustomModal;
