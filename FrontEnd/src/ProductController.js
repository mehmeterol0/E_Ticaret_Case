import React, { useState, useEffect } from 'react';
import './ProductController.scss';
import {
  Card,
  CardContent,
  CardMedia,
  Typography,
  Button,
  CardActions,
  IconButton,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Snackbar,
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';

function ProductController() {
  const [products, setProducts] = useState([]); // Ürünleri tutan state
  const [cart, setCart] = useState([]); // Sepetteki ürünleri tutan state
  const [total, setTotal] = useState(0); // Toplam fiyatı tutan state
  const [snackbarOpen, setSnackbarOpen] = useState(false); // Snackbar görünürlüğü
  const [snackbarMessage, setSnackbarMessage] = useState(''); // Snackbar mesajı
  const [snackbarColor, setSnackbarColor] = useState(''); // Snackbar rengi
  const [loading, setLoading] = useState(true); // Veri yüklenme durumu

  const cartId = 1; // Örnek Cart ID

  useEffect(() => {
    fetchProducts();
    fetchCart();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/products/all');
      if (!response.ok) throw new Error('Ürünler alınamadı!');
      const data = await response.json();
      setProducts(data || []); // Boş gelen durumda güvenli atama
    } catch (error) {
      console.error(error);
      showSnackbar('error', 'Ürünler yüklenirken bir hata oluştu!');
    } finally {
      setLoading(false);
    }
  };

  const fetchCart = async () => {
    try {
      const response = await fetch(`http://localhost:8080/carts/${cartId}`);
      if (!response.ok) throw new Error('Sepet bilgisi alınamadı!');
      const cartData = await response.json();
      setCart(cartData.cartItems || []);
      setTotal(cartData.totalPrice || 0);
    } catch (error) {
      console.error(error);
      showSnackbar('error', 'Sepet bilgisi alınamadı!');
    }
  };

  const increaseQuantity = async (productId) => {
    try {
      const response = await fetch(`http://localhost:8080/carts/carts/${cartId}/products/${productId}/increase`, {
        method: 'PUT',
      });
      if (!response.ok) throw new Error('Miktar arttırılamadı!');
      fetchCart();
      showSnackbar('success', 'Miktar arttırıldı!');
    } catch (error) {
      console.error(error);
      showSnackbar('error', 'Miktar arttırılırken bir hata oluştu!');
    }
  };

  const decreaseQuantity = async (productId) => {
    try {
      const response = await fetch(`http://localhost:8080/carts/carts/${cartId}/products/${productId}/decrease`, {
        method: 'PUT',
      });
      if (!response.ok) throw new Error('Miktar azaltılamadı!');
      fetchCart();
      showSnackbar('success', 'Miktar azaltıldı!');
    } catch (error) {
      console.error(error);
      showSnackbar('error', 'Miktar azaltılırken bir hata oluştu!');
    }
  };

  const emptyCart = async () => {
    try {
      const response = await fetch(`http://localhost:8080/carts/1/empty`, {
        method: 'DELETE',
      });
      fetchCart();
      if (!response.ok) throw new Error('Sepet Boşaltıldı');
    } catch (error) {
      console.error(error);
      showSnackbar('error', 'Sepet Boşaltılamadı');
    }
  };

  const addToCart = async (product) => {
    try {
      const response = await fetch(`http://localhost:8080/carts/${cartId}/products/${product.id}`, {
        method: 'POST',
      });
      if (!response.ok) throw new Error('Ürün sepete eklenemedi!');
      fetchCart();
      showSnackbar('success', 'Ürün sepete eklendi!');
    } catch (error) {
      console.error(error);
      showSnackbar('error', 'Ürün sepete eklenirken bir hata oluştu!');
    }
  };

  const removeFromCart = async (productId) => {
    try {
      const response = await fetch(`http://localhost:8080/carts/${cartId}/products/${productId}`, {
        method: 'DELETE',
      });
      if (!response.ok) throw new Error('Ürün sepetten kaldırılamadı!');
      fetchCart();
      showSnackbar('success', 'Ürün sepetten kaldırıldı!');
    } catch (error) {
      console.error(error);
      showSnackbar('error', 'Ürün sepetten kaldırılırken bir hata oluştu!');
    }
  };

  const placeOrder = async () => {
    try {
      const response = await fetch(`http://localhost:8080/orders/from-cart/${cartId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ cartId }),
      });
      if (!response.ok) throw new Error('Sipariş oluşturulamadı!');
      showSnackbar('success', 'Sipariş başarıyla oluşturuldu!');
      setCart([]);
      setTotal(0);
    } catch (error) {
      console.error(error);
      showSnackbar('error', 'Sipariş oluşturulurken bir hata oluştu!');
    }
  };

  const showSnackbar = (color, message) => {
    setSnackbarColor(color);
    setSnackbarMessage(message);
    setSnackbarOpen(true);
  };

  const handleSnackbarClose = () => setSnackbarOpen(false);

  if (loading) {
    return <div>Yükleniyor...</div>;
  }

  return (
    <div className="ProductPage">
      <div className="ProductList">
        {products.length > 0 ? (
          products.map((product) => (
            <Card key={product.id}>
              <CardMedia component="img" image={product.imageUrl} alt={product.name} />
              <CardContent>
                <Typography variant="h5">{product.name}</Typography>
                <Typography variant="body2"> --------------------------------------------------</Typography>
                <Typography variant="body2">Stok: {product.stock}</Typography>
                <Typography variant="body2"> --------------------------------------------------</Typography>
                <Typography variant="body2">Fiyat: {product.price} ₺</Typography>
              </CardContent>
              <CardActions>
                <Button variant="contained" onClick={() => addToCart(product)}>
                  <AddShoppingCartIcon /> Sepete Ekle
                </Button>
              </CardActions>
            </Card>
          ))
        ) : (
          <Typography>Ürün bulunamadı.</Typography>
        )}
      </div>
      <div className="Cart">
        <TableContainer>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Ürün</TableCell>
                <TableCell>Fiyat</TableCell>
                <TableCell>Miktar</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {cart.map((item) => (
                <TableRow key={item.id}>
                  <TableCell>{item.product ? item.product.name : 'Bilinmiyor'}</TableCell>
                  <TableCell>{item.product ? item.product.price : 0} ₺</TableCell>
                  <TableCell>
                    <IconButton onClick={() => decreaseQuantity(item.product.id)}>
                      <RemoveIcon />
                    </IconButton>
                    {item.quantity}
                    <IconButton onClick={() => increaseQuantity(item.product.id)}>
                      <AddIcon />
                    </IconButton>
                  </TableCell>
                  <TableCell>
                    <IconButton onClick={() => removeFromCart(item.product.id)}>
                      <DeleteIcon />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <div>
          <Typography variant="h6">Toplam: {total} ₺</Typography>
          <Button variant="contained" onClick={placeOrder}>
            Sipariş Ver
          </Button>
          <Button variant="contained" onClick={emptyCart}>
            Sepeti Boşalt
          </Button>
        </div>
      </div>
      <Snackbar
        open={snackbarOpen}
        autoHideDuration={3000}
        onClose={handleSnackbarClose}
        message={snackbarMessage}
        ContentProps={{ sx: { backgroundColor: snackbarColor === 'success' ? 'green' : 'red' } }}
      />
    </div>
  );
}

export default ProductController;
