import * as actionTypes from './action-types';

export const setStores = (stores) => {
  return {
    type: actionTypes.STORES_BY_DATE,
    stores
  }
}