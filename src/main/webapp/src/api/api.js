import axios from "axios";

const defaultOptions = {
/*  baseURL: process.env.HEROKU_BASE_URL !== undefined
      ? process.env.HEROKU_BASE_URL
      : process.env.REACT_APP_LOCALHOST_BASE_URL*/

  baseURL: "https://goods-partner.herokuapp.com/"
};

let axiosWithSetting = axios.create(defaultOptions);

let mockedOrders = {
  data: {
    date: "2022-05-24",
    orders: [
      {
        orderId: 105,
        orderNumber: 32455,
        createdDate: "2022-02-14",
        orderData: {
          clientName: "Novus",
          address: "м. Київ, вул. Межигірська, 5Б",
          managerFullName: "Petya Bamper",
          products: [
            {
              productName: "Margarine",
              amount: 105,
              storeName: "Central Store"
            },
            {
              productName: "Butter",
              amount: 60,
              storeName: "Addition Store"
            }
          ]
        }
      },
      {
        orderId: 155,
        orderNumber: 36755,
        createdDate: "2022-04-10",
        orderData: {
          clientName: "FOP Pupkin",
          address: "м. Київ, вул. Хрещатик, 12",
          managerFullName: "Vasya Manager",
          products: [
            {
              productName: "Sugar",
              amount: 570,
              storeName: "Central Store"
            },
            {
              productName: "Jam",
              amount: 45,
              storeName: "Addition Store"
            }
          ]
        }
      }
    ]
  }
}
let mockedRoutes = {
  data: {
    date: "2022-05-25",
    routes: [
      {
        routeId: 12,
        status: "in progress",
        totalWeight: 568,
        totalPoints: 3,
        totalOrders: 3,
        distance: 157,
        estimatedTime: "7:35:40",
        startTime: "9:24:15",
        finishTime: "",
        spentTime: "",
        routeLink: "https://osm.ua/build-route-12",
        storeName: "main store",
        storeAddress: "м. Фастів, вул. Кільцева, 40",
        routePoints: [
          {
            clientId: 234,
            clientName: "Novus",
            address: "м. Київ, пр. Правди, 47",
            addressTotalWeight: 44,
            orders: [
              {
                orderId: 103,
                orderNumber: 12335
              }
            ]
          },
          {
            clientId: 567,
            clientName: "Silpo",
            address: "вул. Йорданська, 17А, Київ",
            addressTotalWeight: 1097,
            orders: [
              {
                orderId: 105,
                orderNumber: 35545
              },
              {
                orderId: 106,
                orderNumber: 35555
              }
            ]
          }
        ]
      },
      {
        routeId: 13,
        status: "in progress",
        totalWeight: 568,
        totalPoints: 3,
        totalOrders: 3,
        distance: 157,
        estimatedTime: "7:35:40",
        startTime: "9:24:15",
        finishTime: "",
        spentTime: "",
        routeLink: "https://osm.ua/build-route-12",
        storeName: "main store",
        storeAddress: "м. Фастів, вул. Кільцева, 40",
        routePoints: [
          {
            clientId: 234,
            clientName: "Novus",
            address: "вул. Хрещатик, 5, Київ",
            addressTotalWeight: 44,
            orders: [
              {
                orderId: 103,
                orderNumber: 12335
              }
            ]
          },
          {
            clientId: 234,
            clientName: "Novus",
            address: "м. Київ, вул. Сергія Данченка, 5",
            addressTotalWeight: 44,
            orders: [
              {
                orderId: 103,
                orderNumber: 12335
              }
            ]
          },
          {
            clientId: 567,
            clientName: "Novus",
            address: "вулиця Центральна, 2, Ірпінь",
            addressTotalWeight: 1097,
            orders: [
              {
                orderId: 105,
                orderNumber: 35545
              },
              {
                orderId: 106,
                orderNumber: 35555
              }
            ]
          },
          {
            clientId: 567,
            clientName: "Silpo",
            address: "вул. Автопаркова, 7, Київ",
            addressTotalWeight: 1097,
            orders: [
              {
                orderId: 105,
                orderNumber: 35545
              },
              {
                orderId: 106,
                orderNumber: 35555
              }
            ]
          }
        ]
      },
      {
        routeId: 14,
        status: "in progress",
        totalWeight: 568,
        totalPoints: 3,
        totalOrders: 3,
        distance: 157,
        estimatedTime: "7:35:40",
        startTime: "9:24:15",
        finishTime: "",
        spentTime: "",
        routeLink: "https://osm.ua/build-route-12",
        storeName: "main store",
        storeAddress: "м. Фастів, вул. Кільцева, 40",
        routePoints: [
          {
            clientId: 234,
            clientName: "Novus",
            address: "м. Київ, вул. Сергія Данченка, 5",
            addressTotalWeight: 44,
            orders: [
              {
                orderId: 103,
                orderNumber: 12335
              }
            ]
          }
        ]
      },
      {
        routeId: 15,
        status: "in progress",
        totalWeight: 568,
        totalPoints: 3,
        totalOrders: 3,
        distance: 157,
        estimatedTime: "7:35:40",
        startTime: "9:24:15",
        finishTime: "",
        spentTime: "",
        routeLink: "https://osm.ua/build-route-12",
        storeName: "main store",
        storeAddress: "м. Фастів, вул. Кільцева, 40",
        routePoints: []
      }
    ]
  }
}
let mockedStores = {
  data: {
    date: "2022-05-24",
    stores: [
      {
        storeId: 1,
        storeName: "Main Store",
        orders: [
          {
            orderId: 103,
            orderNumber: 12335,
            totalOrderWeight: 107
          },
          {
            orderId: 205,
            orderNumber: 15654,
            totalOrderWeight: 205
          }
        ]
      },
      {
        storeId: 2,
        storeName: "Addition Store",
        orders: [
          {
            orderId: 173,
            orderNumber: 574345,
            totalOrderWeight: 405
          },
          {
            orderId: 245,
            orderNumber: 365675,
            totalOrderWeight: 120
          }
        ]
      }
    ]
  }
}

export const orderApi = {
  getOrdersByDateRequest(ordersDate) {
    return axiosWithSetting.get(`calculate/orders?date=` + ordersDate);
    // mockedOrders.data.date = ordersDate;
    // return mockedOrders;
  }
}

export const routeApi = {
  getRoutesByDateRequest(routesDate) {
    return axiosWithSetting.get(`calculate/routes?date=` + routesDate);
    // mockedRoutes.data.date = routesDate;
    // return mockedRoutes;
  }
}

export const storeApi = {
  getStoresByDateRequest(storesDate) {
    return axiosWithSetting.get(`calculate/stores?date=` + storesDate);
    // mockedStores.data.date = storesDate;
    // return mockedStores;
  }
}