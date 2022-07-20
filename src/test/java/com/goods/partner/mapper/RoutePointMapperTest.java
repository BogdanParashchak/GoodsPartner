package com.goods.partner.mapper;

import com.goods.partner.dto.AddressOrderDto;
import com.goods.partner.dto.RoutePointDto;
import com.goods.partner.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.*;

@TestInstance(PER_CLASS)
class RoutePointMapperTest {
    private RoutePointMapper routePointMapper;
    private Order mockOrder;

    @BeforeEach
    void setUp() {
        routePointMapper = new RoutePointMapper();
        Product mockProduct = mock(Product.class);
        when(mockProduct.getKg()).thenReturn(10.5);

        OrderedProduct mockOrderedProduct = mock(OrderedProduct.class);
        when(mockOrderedProduct.getCount()).thenReturn(5);
        when(mockOrderedProduct.getProduct()).thenReturn(mockProduct);

        Client client = mock(Client.class);
        when(client.getId()).thenReturn(1);
        when(client.getName()).thenReturn("ТОВ \"Хлібзавод\"");

        Address mockAddress = mock(Address.class);
        when(mockAddress.getAddress()).thenReturn("м. Київ, вул. Хрещатик, 1");
        when(mockAddress.getClient()).thenReturn(client);

        mockOrder = mock(Order.class);
        when(mockOrder.getId()).thenReturn(1);
        when(mockOrder.getNumber()).thenReturn(1);
        when(mockOrder.getOrderedProducts()).thenReturn(List.of(mockOrderedProduct));
        when(mockOrder.getAddress()).thenReturn(mockAddress);
    }

    @Test
    @DisplayName("Get order total weight from ordered product list")
    void test_givenOOrderedProduct_whenGetOrderTotalWeight_thenReturnOrderTotalWeight() {
        List<OrderedProduct> orderedProducts = mockOrder.getOrderedProducts();
        double orderTotalWeight = routePointMapper.getOrderTotalWeight(orderedProducts);
        assertEquals(52.5, orderTotalWeight);
    }

    @Test
    @DisplayName("Get AddressOrderDto from Order")
    void test_givenOrder_whenMapOrderAddress_thenReturnAddressOrderDto() {
        RoutePointMapper spyRoutePointMapper = spy(routePointMapper);
        AddressOrderDto addressOrderDto = spyRoutePointMapper.mapOrderAddress(mockOrder);
        assertEquals(1, addressOrderDto.getOrderId());
        assertEquals(1, addressOrderDto.getOrderNumber());
        assertEquals(52.5, addressOrderDto.getOrderTotalWeight());
        verify(spyRoutePointMapper).mapOrderAddress(any(Order.class));
        verify(spyRoutePointMapper).getOrderTotalWeight(anyList());
    }

    @Test
    @DisplayName("Get AddressOrderDto list from Order list")
    void test_givenOrderList_whenMapOrdersAddress_thenReturnAddressOrderDtoList() {
        RoutePointMapper spyRoutePointMapper = spy(routePointMapper);
        List<AddressOrderDto> addressOrderDtos =
                spyRoutePointMapper.mapOrdersAddress(List.of(mockOrder, mockOrder, mockOrder));
        assertEquals(3, addressOrderDtos.size());
        verify(spyRoutePointMapper).mapOrdersAddress(anyList());
        verify(spyRoutePointMapper, times(3)).mapOrderAddress(any(Order.class));
        verify(spyRoutePointMapper, times(3)).getOrderTotalWeight(anyList());
    }

    @Test
    @DisplayName("Get RoutePointDto list from Order list")
    void test_givenOrderList_whenMapOrders_thenReturnRoutePointDtoList() {
        RoutePointMapper spyRoutePointMapper = spy(routePointMapper);
        List<RoutePointDto> routePointDtos = spyRoutePointMapper.mapOrders(List.of(mockOrder));
        assertEquals(1, routePointDtos.size());
        verify(spyRoutePointMapper).mapOrdersAddress(anyList());
        verify(spyRoutePointMapper).mapOrderAddress(any(Order.class));
        verify(spyRoutePointMapper).getOrderTotalWeight(anyList());

        RoutePointDto routePointDto = routePointDtos.get(0);
        assertEquals("м. Київ, вул. Хрещатик, 1", routePointDto.getAddress());
        assertEquals(1, routePointDto.getClientId());
        assertEquals("ТОВ \"Хлібзавод\"", routePointDto.getClientName());
        assertEquals(52.5, routePointDto.getAddressTotalWeight());

        List<AddressOrderDto> orders = routePointDto.getOrders();
        assertEquals(1, orders.size());
    }

    @Test
    @DisplayName("Get RoutePointDto list from Order list, verify grouping by address")
    void test_givenOrderList_whenMapOrders_thenReturnRoutePointDtoList_verifyGroupingByAddress() {
        RoutePointMapper spyRoutePointMapper = spy(routePointMapper);
        List<RoutePointDto> routePointDtos =
                spyRoutePointMapper.mapOrders(List.of(mockOrder, mockOrder, mockOrder));
        assertEquals(1, routePointDtos.size());
        verify(spyRoutePointMapper).mapOrdersAddress(anyList());
        verify(spyRoutePointMapper, times(3)).mapOrderAddress(any(Order.class));
        verify(spyRoutePointMapper, times(3)).getOrderTotalWeight(anyList());

        RoutePointDto routePointDto = routePointDtos.get(0);
        assertEquals("м. Київ, вул. Хрещатик, 1", routePointDto.getAddress());
        assertEquals(1, routePointDto.getClientId());
        assertEquals("ТОВ \"Хлібзавод\"", routePointDto.getClientName());
        assertEquals(157.5, routePointDto.getAddressTotalWeight());

        List<AddressOrderDto> orders = routePointDto.getOrders();
        assertEquals(3, orders.size());
    }
}