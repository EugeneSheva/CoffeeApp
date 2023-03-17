package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Location;
import com.example.coffeapp.Coffee.Model.Order;
import com.example.coffeapp.Coffee.Model.OrderedProduct;
import com.example.coffeapp.Coffee.Model.Product.Product;
import com.example.coffeapp.Coffee.Repository.LocationRepository;
import com.example.coffeapp.Coffee.Repository.OrderRepository;
import com.example.coffeapp.Coffee.Repository.OrderedProductRepository;
import com.example.coffeapp.Coffee.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final OrderRepository orderRepository;
    private final LocationRepository locationRepository;
    private final ProductRepository productRepository;

    private final OrderedProductRepository orderedProductRepository;


    public String LocationNamesListToJS(){
        String names = "";
        int i = 1;
        for (Location location : locationRepository.findAll()) {
            names += location.getAddress();
            if (i++ < locationRepository.findAll().size()) names += "*";
        }
        return names;
    }


    public String LocationOrdersListToJS(){
        String names = "";
        int i = 1;
        for (Location location : locationRepository.findAll()) {
            names += orderRepository.findOrdersByLocationId(location.getId()).size();
            System.out.println(location.getAddress()+" : "+ location.getOrders());
            if (i++ < locationRepository.findAll().size()) names += "*";
        }
        return names;
    }

    public String LocationRevenueListToJS(){
        String reverue = "";
        int i = 1;
        for (Location location : locationRepository.findAll()) {
            Double tmp = 0d;
            for (Order order : orderRepository.findOrdersByLocationId(location.getId())) {
                tmp += order.getPrice();
            }
            reverue += tmp;
            if (i++ < locationRepository.findAll().size()) reverue += "*";
        }
        return reverue;
    }

    public String ProductNamesListToJS(){
        String names = "";
        int i = 1;
        for (Product product : productRepository.findAll()) {
            names += product.getName();
            if (i++ < locationRepository.findAll().size()) names += "*";
        }
        return names;
    }

    public String ProductQuantityListToJS(){
        String quantity = "";
        int i = 1;
        for (Product product : productRepository.findAll()) {
            List<OrderedProduct>orderedProducts = orderedProductRepository.findAllByProduct_Id(product.getId());
            quantity += orderedProducts.size();
            if (i++ < productRepository.findAll().size()) quantity += "*";
        }
        return quantity;
    }

//filter by date

    public String LocationOrdersListByDateToJS(Date from, Date to){
        String names = "";
        int i = 1;
        for (Location location : locationRepository.findAll()) {
            names += orderRepository.findOrdersByDateOfOrderBetweenAndLocation_Id(from, to, location.getId()).size();
            if (i++ < locationRepository.findAll().size()) names += "*";
        }
        return names;
    }

    public String LocationRevenueListByDateToJS(Date from, Date to){
        String reverue = "";
        int i = 1;
        for (Location location : locationRepository.findAll()) {
            Double tmp = 0d;
            for (Order order : orderRepository.findOrdersByDateOfOrderBetweenAndLocation_Id(from, to, location.getId())) {
                tmp += order.getPrice();
            }
            reverue += tmp;
            if (i++ < locationRepository.findAll().size()) reverue += "*";
        }
        return reverue;
    }

    public String ProductQuantityListByDateToJS(Date from, Date to){
        String quantity = "";
        int i = 1;
        for (Product product : productRepository.findAll()) {

            List<OrderedProduct>orderedProducts = orderedProductRepository.findOrderedProductsByDateBetweenAndProduct_Id(from, to, product.getId());
            quantity += orderedProducts.size();
            if (i++ < productRepository.findAll().size()) quantity += "*";
        }
        return quantity;
    }

    public List<String> getProductRating() { return orderedProductRepository.getProductRating();
    }
    public List<String> getProductRatingByDate(Date from, Date to) { return orderedProductRepository.getProductRatingByDate(from, to);
    }
}
