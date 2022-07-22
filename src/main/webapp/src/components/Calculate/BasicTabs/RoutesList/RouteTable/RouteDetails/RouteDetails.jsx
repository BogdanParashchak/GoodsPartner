import Paper from "@mui/material/Paper";
import TableContainer from "@mui/material/TableContainer";
import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableRow from '@mui/material/TableRow';
import {Button, Modal, TableCell} from "@mui/material";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import RouteMap from "../../RoutMap/RouteMap";
import {useLoadScript} from "@react-google-maps/api";

const RouteDetails = (
  {
    date,
    routeId,
    distance,
    estimatedTime,
    finishTime,
    routeLink,
    spentTime,
    startTime,
    status,
    totalOrders,
    totalPoints,
    totalWeight,
    storeName,
    storeAddress,
    routeAddresses
  }) => {

  const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 900,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
  };

  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const {isLoaded} = useLoadScript({
    googleMapsApiKey: process.env.REACT_APP_GOOGLE_MAPS_API_KEY
  })

  return (
    <TableContainer component={Paper} sx={{marginTop: '10px'}}>
      <Table aria-label="spanning table">
        <TableBody>
          <TableRow>
            <TableCell align="left">
              Маршрут №{routeId} від {date}
            </TableCell>
            <TableCell align="left">
              Статус: {status}
            </TableCell>
            <TableCell align="left">
              {
                isLoaded &&
                <>
                  <Button onClick={handleOpen}>Показати на карті</Button>
                  <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                  >
                    <Box sx={style}>
                      <Typography id="modal-modal-title" variant="h6" component="h2">
                        Route #{routeId}
                      </Typography>
                      <Typography id="modal-modal-description">
                        <RouteMap
                          addresses={
                            routeAddresses
                              .filter(routeAdd => routeAdd.routeId === routeId)
                              .map(route => route.addresses)
                          }
                        />
                      </Typography>
                    </Box>
                  </Modal>
                </>
              }
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell align="left">
              Кількість адрес: {totalPoints}
            </TableCell>
            <TableCell align="left">
              Кількість замовлень: {totalOrders}
            </TableCell>
            <TableCell align="left">
              Загальна маса: {totalWeight}
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell align="left">
              Склад: {storeName}
            </TableCell>
            <TableCell colSpan={2} align="left">
              Адреса складу: {storeAddress}
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell colSpan={3} align="left">
              Відстань: {distance} км
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell colSpan={3} align="left">
              Розрахунковий час виконання: {estimatedTime}
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell colSpan={3} align="left">
              Початок виконання: {startTime}
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell colSpan={3} align="left">
              Кінець виконання: {finishTime}
            </TableCell>
          </TableRow>
          <TableRow>
            <TableCell colSpan={3} align="left">
              Фактичний час виконання: {spentTime}
            </TableCell>
          </TableRow>
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default RouteDetails;