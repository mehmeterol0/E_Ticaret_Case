import React from 'react';
import ProductController from './ProductController';
import ResponsiveAppBar from './ResponsiveAppBar';
import TabPanel from './TabPanel';
import OrderList from './OrderList';
import About from './About';
import CustomerForm from './CustomerForm';
import CustomerList from './CustomerList';
import ProductForm from './ProductForm'; 

function App() {
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className="App">
      <header className="App-header">
        <ResponsiveAppBar handleChange={handleChange} />
        <TabPanel value={value} index={0}>
          <ProductController />
        </TabPanel>
        <TabPanel value={value} index={1}>
          <ProductForm />
        </TabPanel>
        <TabPanel value={value} index={2}>
          <OrderList />
        </TabPanel>
        <TabPanel value={value} index={3}>
          <CustomerForm />
        </TabPanel>
        <TabPanel value={value} index={4}>
          <CustomerList />
        </TabPanel>
        <TabPanel value={value} index={5}>
          <About />
        </TabPanel>
      </header>
    </div>
  );
}

export default App;
