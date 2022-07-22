import {Card, CardContent, Stack, TableCell} from "@mui/material";
import Box from "@mui/material/Box";
import TableContainer from "@mui/material/TableContainer";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableBody from "@mui/material/TableBody";
import Paper from "@mui/material/Paper";

const RouteBody = ({routePoints, storeAddress, storeName}) => {
  let isNumberPrinted = false;
  let isAddressPrinted = false;
  let isClientPrinted = false;
  let isWeightPrinted = false;
  let clientNumber = 0;
  return (<Card sx={{minWidth: 275}}>
      <CardContent>
        <Stack spacing={2}>
          <Box sx={{flexGrow: 1}}>
            <TableContainer component={Paper}>
              <Table aria-label="simple table">
                <TableHead sx={{backgroundColor: 'rgba(0, 0, 0, 0.12);'}}>
                  <TableRow>
                    <TableCell sx={{padding: '7px'}} align="center">№</TableCell>
                    <TableCell sx={{padding: '7px'}} align="center">Клієнт</TableCell>
                    <TableCell sx={{padding: '7px'}} align="center">Адреса</TableCell>
                    <TableCell sx={{padding: '7px'}} align="center">Замовлення</TableCell>
                    <TableCell sx={{padding: '7px'}} align="center">Маса, кг</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  <TableRow>
                    <TableCell sx={{padding: '7px'}} align="center">
                      {clientNumber = clientNumber + 1}
                    </TableCell>
                    <TableCell scope="row" sx={{padding: '7px'}} component="th" align="center">
                      {storeAddress}
                    </TableCell>
                    <TableCell sx={{padding: '7px'}} align="center">
                      {storeName}
                    </TableCell>
                    <TableCell colSpan={2}>
                    </TableCell>
                  </TableRow>
                  {
                    routePoints.map(routePoint => (<>
                      {isNumberPrinted = false}
                      {isAddressPrinted = false}
                      {isClientPrinted = false}
                      {isWeightPrinted = false}
                      {
                        routePoint.orders.map(order => (<>
                          <TableRow>
                            {
                              isNumberPrinted === false &&
                              <TableCell rowSpan={routePoint.orders.length}
                                         sx={{padding: '7px'}}
                                         component="th"
                                         scope="row"
                                         align="center">
                                {clientNumber = clientNumber + 1}
                              </TableCell>
                            }
                            {
                              isAddressPrinted === false &&
                              <TableCell rowSpan={routePoint.orders.length}
                                         sx={{padding: '7px'}}
                                         component="th"
                                         align="center"
                                         scope="row">
                                {routePoint.address}
                              </TableCell>
                            }
                            {
                              isClientPrinted === false &&
                              <TableCell rowSpan={routePoint.orders.length} sx={{padding: '7px'}}
                                         align="center">
                                {routePoint.clientName}
                              </TableCell>
                            }
                            <TableCell sx={{padding: '7px'}}
                                       align="center">{order.orderNumber}</TableCell>
                            {
                              isWeightPrinted === false &&
                              <TableCell rowSpan={routePoint.orders.length} sx={{padding: '7px'}}
                                         align="center">{routePoint.addressTotalWeight}</TableCell>
                            }
                            {isNumberPrinted = true}
                            {isAddressPrinted = true}
                            {isClientPrinted = true}
                            {isWeightPrinted = true}
                          </TableRow>
                        </>))
                      }
                    </>))
                  }
                </TableBody>
              </Table>
            </TableContainer>
          </Box>
        </Stack>
      </CardContent>
    </Card>
  );
}

export default RouteBody;