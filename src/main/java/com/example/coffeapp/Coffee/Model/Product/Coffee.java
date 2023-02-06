package com.example.coffeapp.Coffee.Model.Product;


import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Data
@Entity
public class Coffee extends Product {

    String mValue;
    String lValue;
    String xlValue;

    Double mPrice;
    Double lPrice;
    Double xlPrice;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "coffeeId")
    List<CoffeeAdditive> coffeeAdditiveList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coffee)) return false;
        if (!super.equals(o)) return false;
        Coffee coffee = (Coffee) o;
        return Objects.equals(mValue, coffee.mValue) && Objects.equals(lValue, coffee.lValue) && Objects.equals(xlValue, coffee.xlValue) && Objects.equals(mPrice, coffee.mPrice) && Objects.equals(lPrice, coffee.lPrice) && Objects.equals(xlPrice, coffee.xlPrice) && Objects.equals(coffeeAdditiveList, coffee.coffeeAdditiveList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mValue, lValue, xlValue, mPrice, lPrice, xlPrice, coffeeAdditiveList);
    }
}
