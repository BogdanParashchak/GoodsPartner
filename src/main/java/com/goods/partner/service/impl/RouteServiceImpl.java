package com.goods.partner.service.impl;

import com.goods.partner.dto.RouteDto;
import com.goods.partner.dto.RoutePointDto;
import com.goods.partner.dto.StoreDto;
import com.goods.partner.entity.Order;
import com.goods.partner.exceptions.CreateRouteException;
import com.goods.partner.mapper.RoutePointMapper;
import com.goods.partner.service.RouteService;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    @Value("${google.api.key}")
    private String GOOGLE_API_KEY;
    private final RoutePointMapper routePointMapper;


    @Override
    public List<RouteDto> calculateRoutes(List<Order> orders, List<StoreDto> stores) {
        return stores.stream()
                .map(store -> calculateRoute(orders, store))
                .collect(Collectors.toList());
    }

    private RouteDto calculateRoute(List<Order> orders, StoreDto store) {
        List<RoutePointDto> routePoints = routePointMapper.mapOrders(orders);
        DirectionsRoute route = getDirectionRoute(store.getStoreAddress(), routePoints);
        routePoints = getSortedRoutePoints(route.waypointOrder, routePoints);
        double totalDistance = getRouteTotalDistance(route);
        long totalTime = getRouteTotalTime(route);

        return RouteDto.builder()
                .routeId(store.getStoreId())
                .status("NEW")
                .totalWeight(getRouteOrdersTotalWeight(routePoints))
                .totalPoints(route.waypointOrder.length)
                .totalOrders(orders.size())
                .distance(totalDistance)
                .estimatedTime(LocalTime.ofSecondOfDay(totalTime))
                .storeName(store.getStoreName())
                .storeAddress(store.getStoreAddress())
                .routePoints(routePoints)
                .build();
    }

    private DirectionsRoute getDirectionRoute(String startPoint, List<RoutePointDto> routePoints) {
        List<String> pointsAddresses = routePoints.stream()
                .map(RoutePointDto::getAddress).toList();

        try (GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(GOOGLE_API_KEY)
                .build()) {
            return DirectionsApi.newRequest(context)
                    .origin(startPoint)
                    .destination(startPoint)
                    .waypoints(pointsAddresses.toArray(String[]::new))
                    .optimizeWaypoints(true)
                    .mode(TravelMode.DRIVING)
                    .departureTimeNow()
                    .await()
                    .routes[0];
        } catch (IOException | ApiException | InterruptedException e) {
            throw new CreateRouteException(e);
        }
    }

    private List<RoutePointDto> getSortedRoutePoints(int[] waypointOrder, List<RoutePointDto> routePoints) {
        List<RoutePointDto> sortedRoutePoints = new ArrayList<>(1);
        for (int i : waypointOrder) {
            sortedRoutePoints.add(routePoints.get(i));
        }
        return sortedRoutePoints;
    }

    private double getRouteOrdersTotalWeight(List<RoutePointDto> routePoints) {
        return BigDecimal.valueOf(routePoints.stream()
                .map(RoutePointDto::getAddressTotalWeight)
                .collect(Collectors.summarizingDouble(amount -> amount)).getSum())
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private double getRouteTotalDistance(DirectionsRoute route) {
        return BigDecimal.valueOf(Arrays.stream(route.legs).toList().stream()
                .collect(Collectors.summarizingLong(leg -> leg.distance.inMeters)).getSum() / 1000d)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private long getRouteTotalTime(DirectionsRoute route) {
        return Arrays.stream(route.legs).toList().stream()
                .collect(Collectors.summarizingLong(leg -> leg.duration.inSeconds)).getSum();
    }
}
