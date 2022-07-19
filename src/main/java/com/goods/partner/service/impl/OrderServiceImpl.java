package com.goods.partner.service.impl;

import com.goods.partner.dto.*;
import com.goods.partner.entity.Client;
import com.goods.partner.entity.Order;
import com.goods.partner.entity.projection.StoreProjection;
import com.goods.partner.mapper.ClientMapper;
import com.goods.partner.mapper.OrderMapper;
import com.goods.partner.mapper.StoreMapper;
import com.goods.partner.repository.ClientRepository;
import com.goods.partner.repository.OrderRepository;
import com.goods.partner.repository.StoreRepository;
import com.goods.partner.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    DataSource dataSource;
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final StoreRepository storeRepository;
    private final OrderMapper orderMapper;
    private final ClientMapper clientMapper;
    private final StoreMapper storeMapper;

    @Override
    @Transactional
    public CalculationOrdersDto calculateOrders(LocalDate date) {

        List<Order> ordersByDate = orderRepository.findAllByShippingDateEquals(date);
        List<OrderDto> orderDtos = orderMapper.mapOrders(ordersByDate);

        CalculationOrdersDto calculationOrdersDto = new CalculationOrdersDto();
        calculationOrdersDto.setDate(date);
        calculationOrdersDto.setOrders(orderDtos);
        return calculationOrdersDto;
    }

    @Override
    @Transactional
    public CalculationRoutesDto calculateRoutes(LocalDate date) {

        List<Client> clients = clientRepository.findClientsByOrderDate(date);
        List<ClientDto> clientDtos = clientMapper.mapClients(clients);

        //TODO replace this mock DTOs after changes 'routes' table added into DB
        RouteDto routeDtoFirst = RouteDto.builder()
                .routeId(1)
                .status("new")
                .totalWeight(100)
                .totalPoints(5)
                .totalOrders(10)
                .distance(50)
                .estimatedTime(LocalDateTime.MAX)
                .startTime(LocalDateTime.MAX)
                .finishTime(LocalDateTime.MIN)
                .spentTime(LocalDateTime.MIN)
                .routeLink("http//")
                .storeName("storeName1")
                .storeAddress("Київ")
                .clients(clientDtos)
                .build();

        RouteDto routeDtoSecond = RouteDto.builder()
                .routeId(2)
                .status("in progress")
                .totalWeight(200)
                .totalPoints(5)
                .totalOrders(10)
                .distance(50)
                .estimatedTime(LocalDateTime.MAX)
                .startTime(LocalDateTime.MAX)
                .finishTime(LocalDateTime.MIN)
                .spentTime(LocalDateTime.MIN)
                .routeLink("http//")
                .storeName("storeName2")
                .storeAddress("Фастів")
                .clients(clientDtos)
                .build();

        RouteDto routeDtoThird = RouteDto.builder()
                .routeId(3)
                .status("finished")
                .totalWeight(300)
                .totalPoints(5)
                .totalOrders(10)
                .distance(50)
                .estimatedTime(LocalDateTime.MAX)
                .startTime(LocalDateTime.MAX)
                .finishTime(LocalDateTime.MIN)
                .spentTime(LocalDateTime.MIN)
                .routeLink("http//")
                .storeName("storeName3")
                .storeAddress("Одеса")
                .clients(clientDtos)
                .build();

        List<RouteDto> routeDtos = List.of(routeDtoFirst, routeDtoSecond, routeDtoThird);
        CalculationRoutesDto calculationRoutesDto = new CalculationRoutesDto();
        calculationRoutesDto.setDate(date);
        calculationRoutesDto.setRoutes(routeDtos);

        return calculationRoutesDto;
    }

    @Override
    @Transactional
    public CalculationStoresDto calculateStores(LocalDate date) {

        List<StoreProjection> storeProjections = storeRepository.groupStoresByOrders(date);
        List<StoreDto> storeDtos = storeMapper.mapStoreGroup(storeProjections);

        CalculationStoresDto calculationStoresDto = new CalculationStoresDto();
        calculationStoresDto.setDate(date);
        calculationStoresDto.setStores(storeDtos);

        return calculationStoresDto;
    }
}