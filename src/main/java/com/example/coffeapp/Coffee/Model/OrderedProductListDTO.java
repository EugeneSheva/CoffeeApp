package com.example.coffeapp.Coffee.Model;

import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
import com.example.coffeapp.Coffee.Model.Product.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

public class OrderedProductListDTO {
        List<OrderedProduct>orderedProductList;

        public List<OrderedProduct> getOrderedProductList() {
                return orderedProductList;
        }
        public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
                this.orderedProductList = orderedProductList;
        }
        public void addOrderedProductList(OrderedProduct orderedProduct) {
                this.orderedProductList.add(orderedProduct);
        }


}
