package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order>findOrdersByUser_Id(Long id);
    List<Order> findOrdersByLocationId(Long id);

    List<Order> findOrdersByDateOfOrderBetween(Date dateOfOrder, Date dateOfOrder2);

    List<Order> findOrdersByDateOfOrderBetweenAndLocation_Id(Date dateOfOrder, Date dateOfOrder2, Long location_id);
}
