import axios from "axios";

const defaultOptions = {
  //baseURL: 'http://localhost:8080/',
  baseURL: 'https://goods-partner.herokuapp.com/',
};

let axiosWithSetting = axios.create(defaultOptions);

let calculateData = {
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
  ],
  clients: [
    {
      clientId: 234,
      clientName: "Novus",
      // totalOrdersAmount: "",
      addresses: [
        {
          address: "м. Київ, вул. Межигірська, 5Б",
          addressTotalWeight: 244,
          orders: [
            {
              orderId: 103,
              orderNumber: 12335
            },
            {
              orderId: 205,
              orderNumber: 15654
            }
          ]
        },
        {
          address: "м. Київ, вул. Хрещатик, 10",
          addressTotalWeight: 589,
          orders: [
            {
              orderId: 255,
              orderNumber: 153325
            },
            {
              orderId: 289,
              orderNumber: 2053455
            },
            {
              orderId: 278,
              orderNumber: 17688
            }
          ]
        }
      ]
    },
    {
      clientId: 432,
      clientName: "FOP Pupkin",
      addresses: [
        {
          address: "м. Київ, вул. Межигірська, 5Б",
          addressTotalWeight: 244,
          orders: [
            {
              orderId: 103,
              orderNumber: 12335
            },
            {
              orderId: 205,
              orderNumber: 15654
            }
          ]
        }
      ]
    }
  ],
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

export const calculateApi = {
  calculateByDateRequest(ordersDate) {
    return axiosWithSetting.get(`calculate?date=` + ordersDate);
    // calculateData.date = ordersDate;
    // return calculateData;
  }
}