import * as React from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import OrdersList from "./OrdersList/OrdersList";
import StoreTable from "./StoreTable/StoreTable";
import RoutesList from "./RoutesList/RoutesList";

function TabPanel(props) {
  const {children, value, index, ...other} = props;

  return (
      <div
          role="tabpanel"
          hidden={value !== index}
          id={`simple-tabpanel-${index}`}
          aria-labelledby={`simple-tab-${index}`}
          {...other}
      >
        {value === index && (
            <Box sx={{p: 3}}>
              <Typography>{children}</Typography>
            </Box>
        )}
      </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  };
}

export default function BasicTabs({orders, routes, stores, routeAddresses}) {
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
      <Box sx={{width: '100%'}}>
        <Box sx={{borderBottom: 1, borderColor: 'divider'}}>
          <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
            <Tab label="Замовлення" {...a11yProps(0)} />
            <Tab label="Маршрути" {...a11yProps(1)} />
            <Tab label="Склади" {...a11yProps(2)} />
          </Tabs>
        </Box>
        <TabPanel value={value} index={0}>
          <OrdersList
              date={orders.date}
              orders={orders.orders}
          />
        </TabPanel>
        <TabPanel value={value} index={1}>
          <RoutesList
              date={routes.date}
              routes={routes.routes}
              routeAddresses={routeAddresses}
          />
        </TabPanel>
        <TabPanel value={value} index={2}>
          <StoreTable
              date={stores.date}
              stores={stores.stores}
          />
        </TabPanel>
      </Box>
  );
}
