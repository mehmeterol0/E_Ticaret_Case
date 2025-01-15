import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';
import axios from 'axios';

const ProductForm = () => {
  const [product, setProduct] = useState({
    name: '',
    price: '',
    stock: '',
    imageUrl: ''
  });
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [snackbarSeverity, setSnackbarSeverity] = useState('success');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleCloseSnackbar = () => {
    setSnackbarOpen(false);
  };

  const handleSnackbar = (message, severity) => {
    setSnackbarMessage(message);
    setSnackbarSeverity(severity);
    setSnackbarOpen(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        'http://localhost:8080/api/products/create',
        product,
        {
          headers: {
            'Content-Type': 'application/json; charset=utf-8'
          }
        }
      );
      console.log('Product added successfully:', response.data);
      handleSnackbar('Ürün Başarıyla Eklendi', 'success');
      window.location.reload(); 
    } catch (error) {
      console.error('Error adding product:', error);
      handleSnackbar('Ürün Eklenirken Hata Oluştu', 'error');
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <TextField
          name="name"
          label="Ürün Adı"
          value={product.name}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          name="price"
          label="Ürün Fiyatı"
          value={product.price}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          name="stock"
          label="Stok Adedi"
          value={product.stock}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          name="imageUrl"
          label="Resim URL"
          value={product.imageUrl}
          onChange={handleChange}
          fullWidth
          margin="normal"
        />
        <Button type="submit" variant="contained" color="primary">
          Ürünü Ekle
        </Button>
      </form>
      <Snackbar
        anchorOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }}
        open={snackbarOpen}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
      >
        <MuiAlert
          elevation={6}
          variant="filled"
          onClose={handleCloseSnackbar}
          severity={snackbarSeverity}
        >
          {snackbarMessage}
        </MuiAlert>
      </Snackbar>
    </div>
  );
};

export default ProductForm;
