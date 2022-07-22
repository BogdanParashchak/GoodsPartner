import React, {useState} from "react";
import {DirectionsRenderer, GoogleMap, Marker} from "@react-google-maps/api";
import {Button} from "@mui/material";
import Geocode from "react-geocode";

function timeConvert(n) {
  let hours = (n / 60);
  let rhours = Math.floor(hours);
  let minutes = (hours - rhours) * 60;
  let rminutes = Math.round(minutes);
  return rhours + " год : " + rminutes + " хв";
}

async function getLatLng(address) {
  Geocode.setApiKey("AIzaSyD0M9GvjU-nzoPjTWJCsVuG4jDyAvqHvHc");
  Geocode.setLanguage("uk");
  Geocode.setRegion("es");
  Geocode.setLocationType("ROOFTOP");
  Geocode.enableDebug();

  let promise = Geocode.fromAddress(address).then(
    (response) => {
      const {lat, lng} = response.results[0].geometry.location;
      return {lat, lng};
    },
    (error) => {
      console.error(error);
      return {zxc: "qqq"}
    }
  );
  return await promise;
}

const RouteMap = ({addresses}) => {

  const [map, setMap] = useState(/** @type google.maps.Map*/null);
  const [directionsResponse, setDirectionsResponse] = useState(null)
  const [distance, setDistance] = useState(null)
  const [duration, setDuration] = useState("")
  const [marker, setMarker] = useState({
    lat: 50.449217392381776,
    lng: 30.450244577069448
  })

  async function calculateRoute(inputAddresses) {
    let addresses = structuredClone(inputAddresses);
    const origin = addresses[0].shift();
    const dest = addresses[0].length > 0 ? addresses[0].pop() : origin;
    let wayPoints = addresses[0].length > 0 ? addresses[0].map(addr => {
      return {location: addr, stopover: true}
    }) : [];
    // eslint-disable-next-line no-undef
    const directionService = new google.maps.DirectionsService()
    getLatLng(origin).then(resp => setMarker(resp))
    const result = await directionService.route({
      origin: origin,
      destination: dest,
      // eslint-disable-next-line no-undef
      travelMode: google.maps.TravelMode.DRIVING,
      // waypoints: [{location: "м. Київ, вул. Хрещатик, 5", stopover: true}, {location: "м. Київ, вул. Сергія Данченка, 5", stopover: true}]
      waypoints: wayPoints
      // optimizeWaypoints: true
    })

    setDirectionsResponse(result)

    let totalDistance = result.routes[0].legs.map(leg => leg.distance.value).reduce((a, b) => a + b, 0)
    let totalTime = result.routes[0].legs.map(leg => leg.duration.value).reduce((a, b) => a + b, 0)

    setDistance(totalDistance / 1000)
    setDuration(timeConvert((totalTime / 60).toFixed(0)))
  }

  return <div style={
    {
      width: 835,
      height: 600
    }
  }>
    <div>
      <Button style={{paddingLeft: 15}} onClick={() => map.panTo(marker)}>Go to start point</Button>
      <Button onClick={() => calculateRoute(addresses)}>build</Button>
    </div>
    <div>
      <span style={{paddingRight: "25px"}}>
        Distance: {distance} km
      </span>
        Duration: {duration}
    </div>
    <GoogleMap
      center={marker}
      zoom={15}
      mapContainerStyle={
        {
          width: '100%',
          height: '95%'
        }
      }
      onLoad={(map) => setMap(map)}
    >
      <Marker position={marker}/>
      {
        directionsResponse && <DirectionsRenderer directions={directionsResponse}/>
      }
    </GoogleMap>
  </div>
}
export default RouteMap;