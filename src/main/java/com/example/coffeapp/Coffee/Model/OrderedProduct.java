package com.example.coffeapp.Coffee.Model;

import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
import com.example.coffeapp.Coffee.Model.Product.Product;
import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
public class OrderedProduct {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "product_id")
        Product product;



        @ManyToMany
        @JoinTable( name = "coffe_additives_in_ordered_product",
                joinColumns = { @JoinColumn(name = "ordered_product_id") },
                inverseJoinColumns = { @JoinColumn(name = "coffee_additive_id") } )
        List<CoffeeAdditive> coffeeAdditiveList;

        Long quantity;
        String size;
        Double price;

//        @ManyToOne(fetch = FetchType.EAGER)
//        @JoinColumn (name = "orderId")
//        Order order;

        public String getAdditivesNames() {
                String names = "";
                if (coffeeAdditiveList != null && coffeeAdditiveList.size()>0) {
                        int i = 1;
                        names += " c (";
                        for (CoffeeAdditive coffeeAdditive : coffeeAdditiveList) {

                                names += coffeeAdditive.getName();
                                if (i < coffeeAdditiveList.size()) names += ", ";
                                i++;
                        }
                        names += ")";
                }
                return names;
        }
        public String getAdditivesNames2() {
                String names = "";
                if (coffeeAdditiveList != null && coffeeAdditiveList.size()>0) {
                        int i = 1;
                        for (CoffeeAdditive coffeeAdditive : coffeeAdditiveList) {

                                names += " " + coffeeAdditive.getName();
                                if (i < coffeeAdditiveList.size()) names += ",";
                                i++;
                        }
                        names += ".";
                }
                return names;
        }


}
