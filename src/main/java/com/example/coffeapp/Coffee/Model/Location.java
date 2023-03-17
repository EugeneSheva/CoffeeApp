package com.example.coffeapp.Coffee.Model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String address;
    Integer phoneNunber;
    Integer coordinates;
    String description;
    String image;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="location")
    private List<Order> orders;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phoneNunber=" + phoneNunber +
                ", coordinates=" + coordinates +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", orders=" + orders.size() +
                '}';
    }
}
