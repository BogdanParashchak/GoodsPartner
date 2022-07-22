import * as actionTypes from './action-types';

export const setOrders = (orders) => {
  return {
    type: actionTypes.ORDERS_BY_DATE,
    orders
  }
}