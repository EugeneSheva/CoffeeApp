package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.OrderedProduct;
import com.example.coffeapp.Coffee.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {
}
