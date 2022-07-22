package com.goods.partner.mapper;

import com.goods.partner.dto.AddressOrderDto;
import com.goods.partner.dto.RoutePointDto;
import com.goods.partner.entity.Address;
import com.goods.partner.entity.Order;
import com.goods.partner.entity.OrderedProduct;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RoutePointMapper {

    public List<RoutePointDto> mapOrders(List<Order> orders) {
        Map<Address, List<Order>> addressOrderMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getAddress));

        List<RoutePointDto> routePointDtoList = new ArrayList<>(1);
        addressOrderMap.forEach((address, orderList) -> {
            List<AddressOrderDto> addressOrderDtos = mapOrdersAddress(orderList);
            double addRessTotalWeight = addressOrderDtos.stream()
                    .map(AddressOrderDto::getOrderTotalWeight)
                    .collect(Collectors.summarizingDouble(amount -> amount)).getSum();

            RoutePointDto routePointDto = new RoutePointDto();
            routePointDto.setAddress(address.getAddress());
            routePointDto.setClientId(address.getClient().getId());
            routePointDto.setClientName(address.getClient().getName());
            routePointDto.setOrders(addressOrderDtos);
            routePointDto.setAddressTotalWeight(addRessTotalWeight);
            routePointDtoList.add(routePointDto);
        });
        return routePointDtoList;
    }

    @VisibleForTesting
    List<AddressOrderDto> mapOrdersAddress(List<Order> orders) {
        return orders.stream()
                .map(this::mapOrderAddress)
                .collect(Collectors.toList());
    }

    @VisibleForTesting
    AddressOrderDto mapOrderAddress(Order order) {
        List<OrderedProduct> orderedProducts = order.getOrderedProducts();
        double orderTotalWeight = getOrderTotalWeight(orderedProducts);

        AddressOrderDto addressOrderDto = new AddressOrderDto();
        addressOrderDto.setOrderId(order.getId());
        addressOrderDto.setOrderNumber(order.getNumber());
        addressOrderDto.setOrderTotalWeight(orderTotalWeight);

        return addressOrderDto;
    }

    @VisibleForTesting
    double getOrderTotalWeight(List<OrderedProduct> orderedProducts) {
        return BigDecimal.valueOf(orderedProducts.stream()
                        .map(orderedProduct -> orderedProduct.getCount() * orderedProduct.getProduct().getKg())
                        .collect(Collectors.summarizingDouble(amount -> amount)).getSum())
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
