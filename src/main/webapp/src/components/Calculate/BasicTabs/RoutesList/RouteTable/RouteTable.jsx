import * as React from 'react';
import RouteDetails from "./RouteDetails/RouteDetails";
import RouteBody from "./RouteBody/RouteBody";

const RouteTable = (
  {
    date,
    route,
    routeAddresses
  }) => {
  return (<>
        <RouteDetails
            routeId={route.routeId}
            status={route.status}
            totalWeight={route.totalWeight}
            totalPoints={route.totalPoints}
            totalOrders={route.totalOrders}
            distance={route.distance}
            estimatedTime={route.estimatedTime}
            startTime={route.startTime}
            finishTime={route.finishTime}
            spentTime={route.spentTime}
            routeLink={route.routeLink}
            date={date}
            storeName={route.storeName}
            storeAddress={route.storeAddress}
            routeAddresses={routeAddresses}
        />
        <RouteBody
            routePoints={route.routePoints}
            storeName={route.storeName}
            storeAddress={route.storeAddress}
        />
      </>
  );
}

export default RouteTable;