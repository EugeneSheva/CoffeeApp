package com.example.coffeapp.Coffee.Model.Additives;

import com.example.coffeapp.Coffee.Model.Location;
import com.example.coffeapp.Coffee.Model.OrderedProduct;
import lombok.Data;

import javax.persistence.*;
import java.util.List;



@Entity
@Data
public class CoffeeAdditive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Double price;
    @Enumerated (EnumType.STRING)
    CoffeeAdditiveType coffeeAdditiveType;

//    @ManyToMany(mappedBy = "coffeeAdditiveList")
//    List<OrderedProduct> orderedProductList;

}
