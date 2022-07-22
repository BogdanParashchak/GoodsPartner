import Box from '@mui/material/Box';
import {Card, CardContent, Stack, Typography} from "@mui/material";

import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

function StoreTable({date, stores}) {
  let isStorePrinted = false;
  return (<Card sx={{minWidth: 275}}>
        <CardContent>
          <Stack spacing={2}>
            {
              date
                  ? <>
                    <Typography variant="h5" gutterBottom component="div">
                      Список замовлень на {date}
                    </Typography>
                    <Box sx={{flexGrow: 1}}>
                      <TableContainer component={Paper}>
                        <Table aria-label="simple table">
                          <TableHead sx={{backgroundColor: 'rgba(0, 0, 0, 0.12);'}}>
                            <TableRow>
                              <TableCell sx={{padding: '7px'}}>Склад</TableCell>
                              <TableCell sx={{padding: '7px'}} align="right">Замовлення</TableCell>
                              <TableCell sx={{padding: '7px'}} align="right">Маса, кг</TableCell>
                            </TableRow>
                          </TableHead>
                          <TableBody>
                            {stores.map((store) => (<>
                                  {isStorePrinted = false}
                                  {store.orders.map(order =>
                                      <>
                                        <TableRow
                                            key={store.storeName}
                                            sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                        >
                                          {
                                            isStorePrinted === false &&
                                            <TableCell rowSpan={store.orders.length} sx={{padding: '7px'}} component="th"
                                                       scope="row">
                                              {store.storeName}
                                            </TableCell>
                                          }
                                          <TableCell sx={{padding: '7px'}} component="th" scope="row" align="right">
                                            {order.orderNumber}
                                          </TableCell>
                                          <TableCell sx={{padding: '7px'}} component="th" scope="row" align="right">
                                            {order.totalOrderWeight}
                                          </TableCell>
                                          {isStorePrinted = true}
                                        </TableRow>
                                      </>
                                  )}
                                </>
                            ))}
                          </TableBody>
                        </Table>
                      </TableContainer>
                    </Box>
                  </>
                  : <Typography variant="h5" gutterBottom component="div">
                    Оберіть дату та натисніть "РОЗРАХУВАТИ"
                  </Typography>
            }

          </Stack>
        </CardContent>
      </Card>
  );
}

export default StoreTable;
