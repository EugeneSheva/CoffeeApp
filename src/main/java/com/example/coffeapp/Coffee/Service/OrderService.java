package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Order;
import com.example.coffeapp.Coffee.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public Order findById (Long id) { return orderRepository.getReferenceById(id); }

    public List<Order> findAll() { return orderRepository.findAll(); }

    public Order save(Order order) { return orderRepository.save(order); }

    public void deleteById(Long id) { orderRepository.deleteById(id); }

    public List<Order>findOrdersByUser_Id(Long id) {return orderRepository.findOrdersByUser_Id(id);}

    public List<Order>findOrdersByLocationId(Long id) {return orderRepository.findOrdersByLocationId(id);}

    public List<Order>findOrdersByDateOfOrderBetween(Date from, Date to) {return orderRepository.findOrdersByDateOfOrderBetween(from, to);}


}
