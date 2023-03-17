package com.example.coffeapp.Coffee.Repository;


import com.example.coffeapp.Coffee.Model.Order;
import com.example.coffeapp.Coffee.Model.OrderedProduct;
import com.example.coffeapp.Coffee.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {
    List<OrderedProduct> findAllByProduct_Id(Long id);
    List<OrderedProduct> findOrderedProductsByDateBetweenAndProduct_Id(Date dateOfOrder, Date dateOfOrder2, Long product_id);

    @Query("SELECT p.name, Sum(op.quantity) FROM OrderedProduct op join Product p on op.product = p group by p.name")
    List<String> getProductRating();

    @Query("SELECT p.name, Sum(op.quantity) FROM OrderedProduct op join Product p on op.product = p where op.date between ?1 and ?2 group by p.name")
    List<String> getProductRatingByDate(Date dateOfOrder, Date dateOfOrder2);


}
