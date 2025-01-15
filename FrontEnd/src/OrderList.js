import * as React from 'react';
import { useState, useEffect } from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

function OrderList() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {
    try {
      const orderIds = Array.from(Array(999).keys()); 
      const response = await fetch(`http://localhost:8080/price-history/orders?orderIds=${orderIds.join(',')}`);
      console.log(response);
      if (!response.ok) {
        throw new Error('HTTP Error! Status: ' + response.status);
      }
      const data = await response.json();
      console.log(data);
      const formattedData = data.map((item, index) => ({
        id: index + 1,
        productName: item.productName,
        price: item.price,
        orderDate: item.orderDate,
        orderId: item.orderId
      }));
      setOrders(formattedData);
    } catch (error) {
      console.error('Error fetching orders:', error);
    }
  };

  const colorByOrderId = (orderId) => {
    const colors = ['#ff9999', '#99ff99', '#9999ff', '#ffff99', '#ff99ff', '#99ffff', '#ffcc99', '#ccff99', '#99ccff', '#ff99cc'];
    const index = orderId % colors.length;
    return colors[index];
  };

  return (
    <TableContainer component={Paper}>
      <Table aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Sıra</StyledTableCell>
            <StyledTableCell>Sipariş Numarası</StyledTableCell>
            <StyledTableCell>Ürün Adı</StyledTableCell>
            <StyledTableCell align="right">Fiyat</StyledTableCell>
            <StyledTableCell align="right">Sipariş Zamanı</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {orders.map((order,index) => (
            <StyledTableRow key={order.id} style={{ backgroundColor: colorByOrderId(order.orderId) }}>
              <StyledTableCell>{index+1}</StyledTableCell>
              <StyledTableCell>{order.orderId}</StyledTableCell>
              <StyledTableCell>{order.productName}</StyledTableCell>
              <StyledTableCell align="right">{order.price} ₺</StyledTableCell>
              <StyledTableCell align="right">{order.orderDate}</StyledTableCell>
            </StyledTableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
export default OrderList;
