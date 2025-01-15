import React from 'react';
import './ShoppingCart.scss';
import { Button, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

const ShoppingCart = ({ cart, total, removeFromCart, placeOrder }) => {
  return (
    <div className="ShoppingCart">
      <h2>Sepet</h2>
      {cart && cart.length > 0 ? (
        <>
          {cart.map((item) => (
            <div key={item.id} className="CartItem">
              <span>{item.name}</span>
              <span>{item.price} ₺</span>
              <IconButton aria-label="delete" onClick={() => removeFromCart(item.id)}>
                <DeleteIcon />
              </IconButton>
            </div>
          ))}
          <div className="Total">
            <span>Toplam:</span>
            <span>{total} ₺</span>
          </div>
          <Button variant="contained" color="primary" onClick={placeOrder} style={{ marginTop: '10px' }}>
            Sipariş Ver
          </Button>
        </>
      ) : (
        <div>Sepet Boş</div>
      )}
    </div>
  );
};

export default ShoppingCart;
