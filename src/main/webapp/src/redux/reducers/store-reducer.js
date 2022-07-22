import * as actionTypes from '../actions/action-types';

let initialStores = {
  date: "",
  stores: [
    {
      storeId: null,
      storeName: "",
      orders: [
        {
          orderId: null,
          orderNumber: null,
          kg: null
        }
      ]
    }
  ]
}

const storeReducer = (state = initialStores, action) => {
  switch (action.type) {
    case actionTypes.STORES_BY_DATE:
      return {
        date: action.stores.date,
        stores: action.stores.stores
      }
    default:
      return state;
  }
}

export default storeReducer;