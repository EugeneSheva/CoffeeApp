package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.OrderedProduct;
import com.example.coffeapp.Coffee.Repository.OrderedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderedProductService {

    private final OrderedProductRepository orderedProductRepository;


    public OrderedProduct findById (Long id) { return orderedProductRepository.getReferenceById(id); }

    public List<OrderedProduct> findAll() { return orderedProductRepository.findAll(); }

    public OrderedProduct save(OrderedProduct orderedProduct) { return orderedProductRepository.save(orderedProduct); }

    public void deleteById(Long id) { orderedProductRepository.deleteById(id); }
}
