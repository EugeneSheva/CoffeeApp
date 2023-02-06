package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Product.Coffee;
import com.example.coffeapp.Coffee.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
