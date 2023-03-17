package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Product.Coffee;
import com.example.coffeapp.Coffee.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
