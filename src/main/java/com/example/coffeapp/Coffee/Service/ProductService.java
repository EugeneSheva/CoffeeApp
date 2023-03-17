package com.example.coffeapp.Coffee.Service;
import com.example.coffeapp.Coffee.Model.Product.Product;
import com.example.coffeapp.Coffee.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Product findById (Long id) { return productRepository.getReferenceById(id); }

    public List<Product> findAll() { return productRepository.findAll(); }

    public Product save(Product product) { return productRepository.save(product); }

    public void deleteById(Long id) { productRepository.deleteById(id); }

}
