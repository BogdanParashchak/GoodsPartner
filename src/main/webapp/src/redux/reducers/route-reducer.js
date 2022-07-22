import * as actionTypes from '../actions/action-types';

let initialRoutes = {
  date: "",
  routes: [
    {
      routeId: null,
      status: "",
      totalWeight: null,
      totalPoints: null,
      totalOrders: null,
      distance: null,
      estimatedTime: "",
      startTime: "",
      finishTime: "",
      spentTime: "",
      routeLink: "", //maybe unnecessary
      storeName: "",
      storeAddress: "",
      routePoints: [
        {
          clientId: null,
          clientName: "",
          address: "",
          addressTotalWeight: null,
          orders: [
            {
              orderId: null,
              orderNumber: null
            }
          ]
        }
      ]
    }
  ]
}

const routeReducer = (state = initialRoutes, action) => {
  switch (action.type) {
    case actionTypes.ROUTES_BY_DATE:
      return {
        date: action.routes.date,
        routes: action.routes.routes
      }
    default:
      return state;
  }
}

export default routeReducer;