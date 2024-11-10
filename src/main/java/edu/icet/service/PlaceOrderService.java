package edu.icet.service;

import edu.icet.dto.Order;

import java.util.List;

public interface PlaceOrderService {
    void placeOrder(Order order);

    List<Order> getOrderByCustomer(String email);

    List<Order> getAll();
}
