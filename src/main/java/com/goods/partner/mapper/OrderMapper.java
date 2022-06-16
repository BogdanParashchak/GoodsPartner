package com.goods.partner.mapper;

import com.goods.partner.dto.OrderData;
import com.goods.partner.dto.OrderDto;
import com.goods.partner.dto.ProductDto;
import com.goods.partner.entity.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public List<OrderDto> mapOrders(List<Order> orders) {
        return orders.stream()
                .map(this::mapOrder)
                .collect(Collectors.toList());
    }

    private OrderDto mapOrder(Order order) {
        Address address = order.getAddress();
        Client client = address.getClient();

        //LocalDate date = order.getDate();

        Manager manager = order.getManager();

        List<ProductDto> products = mapProducts(order.getOrderedProducts());

        OrderData orderData = new OrderData();
        orderData.setClientName(client.getName());
        orderData.setAddress(address.getAddress());
        orderData.setManagerFullName(manager.getFirstName() + " " + manager.getLastName()); // TODO check with Taras to have single field for this
        orderData.setProducts(products);

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getId());
        orderDto.setOrderNumber(order.getNumber());

        orderDto.setCreatedDate(order.getDate());
        orderDto.setOrderData(orderData);

        return orderDto;
    }

    private List<ProductDto> mapProducts(List<OrderedProduct> products) {
        return products.stream()
                .map(this::mapProduct)
                .collect(Collectors.toList());
    }

    private ProductDto mapProduct(OrderedProduct orderedProduct) {
        Product product = orderedProduct.getProduct();
        Store store = product.getStore();

        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getName());
        productDto.setAmount(orderedProduct.getCount());
        productDto.setStoreName(store.getName());
        return productDto;
    }

}
