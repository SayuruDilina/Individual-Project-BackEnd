package edu.icet.controller;

import edu.icet.dto.Order;
import edu.icet.service.PlaceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PlaceOrderController {

    private  final PlaceOrderService service;

    @PostMapping("/place-order")
    public void placeOrder(@RequestBody Order order){
        service.placeOrder(order);
    }
    @GetMapping("/get-order-by-customer")
    public List<Order> getOrdersByCustomer(@RequestParam("email") String email){
        return service.getOrderByCustomer(email);

    }
    @GetMapping("get-all-orders")
    public List<Order> getAll(){
        return service.getAll();
    }
}
