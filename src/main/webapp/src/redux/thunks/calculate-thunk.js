import {orderApi, routeApi, storeApi} from "../../api/api";
import {setOrders} from "../actions/order-action";
import {setRoutes} from "../actions/route-action";
import {setStores} from "../actions/store-action";

export const getCalculatedDataByDate = (date) => async dispatch => {
  let orders = await orderApi.getOrdersByDateRequest(date);
  let routes = await routeApi.getRoutesByDateRequest(date);
  let stores = await storeApi.getStoresByDateRequest(date);
  dispatch(setOrders(orders.data));
  dispatch(setRoutes(routes.data));
  dispatch(setStores(stores.data));
}