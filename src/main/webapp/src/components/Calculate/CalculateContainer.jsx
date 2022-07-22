import React from "react";
import Calculate from "./Calculate";
import {connect} from "react-redux";
import {compose} from "redux";
import {getCalculatedDataByDate} from "../../redux/thunks/calculate-thunk";
import Geocode from "react-geocode";

// async function getLatLng(address) {
//   Geocode.setApiKey("AIzaSyD0M9GvjU-nzoPjTWJCsVuG4jDyAvqHvHc");
//   Geocode.setLanguage("uk");
//   Geocode.setRegion("es");
//   Geocode.setLocationType("ROOFTOP");
//   Geocode.enableDebug();
//
//   let promise = Geocode.fromAddress(address).then(
//     (response) => {
//       const {lat, lng} = response.results[0].geometry.location;
//       return {lat, lng};
//     },
//     (error) => {
//       console.error(error);
//       return {zxc: "qqq"}
//     }
//   );
//   return await promise;
// }
//
// async function getAddresses(routes){
//
//   return await routes.map(route => {
//     let addresses = route.routePoints.map(routePoint => routePoint.address);
//     addresses.unshift(route.storeAddress);
//
//     let latLngs = [];
//
//     addresses.map(address => getLatLng(address).then(resp => {
//       latLngs.push(resp)
//     }))
//     return {
//       routeId: route.routeId,
//       addresses: addresses,
//       latLngs: latLngs
//     }
//   });
// }

class CalculateContainer extends React.Component {

  getCalculatedDataByDate = (dateForCalculation) => {
    this.props.getCalculatedDataByDate(dateForCalculation);
  }

  getAddresses = () => {

    return this.props.routeData.routes.map(route => {
      let addresses = route.routePoints.map(routePoint => routePoint.address);
      addresses.unshift(route.storeAddress);
      return {
        routeId: route.routeId,
        addresses: addresses
      }
    });
  }

  render() {
    let routeAddresses = this.props.routeData.date === "" ? "" : this.getAddresses(this.props.routeData.routes);
    return <Calculate
      orders={this.props.orderData}
      routes={this.props.routeData}
      stores={this.props.storeData}
      routeAddresses={routeAddresses}
      getCalculatedDataByDate={this.getCalculatedDataByDate}
    />
  }
}

let mapStateToProps = (state) => {
  return {
    orderData: state.orders,
    routeData: state.routes,
    storeData: state.stores
  }
}

export default compose(connect(mapStateToProps, {getCalculatedDataByDate}))(CalculateContainer);