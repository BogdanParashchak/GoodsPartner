import {applyMiddleware, combineReducers, compose, legacy_createStore} from "redux";
import thunkMiddleware from "redux-thunk";
import orderReducer from "./reducers/order-reducer";
import routeReducer from "./reducers/route-reducer";
import storeReducer from "./reducers/store-reducer";

let reducers = combineReducers({
  orders: orderReducer,
  routes: routeReducer,
  stores: storeReducer
});

const composeEnhancers = (window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__({
  trace: true,
  traceLimit: 25
})) || compose;

const store = legacy_createStore(reducers, composeEnhancers(applyMiddleware(thunkMiddleware)
));

window.reactStore = store;

export default store;