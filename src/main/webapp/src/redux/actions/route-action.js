import * as actionTypes from './action-types';

export const setRoutes = (routes) => {
  return {
    type: actionTypes.ROUTES_BY_DATE,
    routes
  }
}