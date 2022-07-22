import Container from "@mui/material/Container";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import DateChooserCard from "./DateChooserCard/DateChooserCard";
import BasicTabs from "./BasicTabs/BasicTabs";
import React from "react";

const Calculate = ({orders, routes, stores, getCalculatedDataByDate, routeAddresses}) => {
  return (
      <div className="App">
        <Container>
          <Box sx={{flexGrow: 1}}>
            <Grid container spacing={15}>
              <Grid item xs={2}>
                <DateChooserCard
                    getCalculatedDataByDate={getCalculatedDataByDate}
                />
              </Grid>
              <Grid item xs={10}>
                <BasicTabs
                    orders={orders}
                    routes={routes}
                    stores={stores}
                    routeAddresses={routeAddresses}
                />
              </Grid>
            </Grid>
          </Box>
        </Container>
      </div>
  );
}

export default Calculate;