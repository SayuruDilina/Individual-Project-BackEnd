package edu.icet.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.AccommodationPackage;
import edu.icet.dto.Order;
import edu.icet.entity.OrderEntity;
import edu.icet.repository.AccommodationRepository;
import edu.icet.repository.OrderRepository;
import edu.icet.service.PlaceOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceOrderServiceImpl implements PlaceOrderService {

    private final AccommodationRepository accommodationRepository;
    private  final OrderRepository repository;
    private final ObjectMapper mapper;

    @Override
    @Transactional
    public void placeOrder(Order order) {

          accommodationRepository.updateQuantity(order.getQty(),order.getPackageID());
          repository.save(mapper.convertValue(order, OrderEntity.class));
    }

    @Override
    public List<Order> getOrderByCustomer(String email) {
        List<Order> orderList=new ArrayList<>();
        List<OrderEntity> orderEntityList = repository.findByEmail(email);
        orderEntityList.forEach(orderEntity ->{
            orderList.add(mapper.convertValue(orderEntity,Order.class));
        });

        return orderList;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orderList=new ArrayList<>();
        List<OrderEntity> all = repository.findAll();
        all.forEach(orderEntity -> {
            orderList.add(mapper.convertValue(orderEntity, Order.class));
        });
        return orderList;
    }
}
