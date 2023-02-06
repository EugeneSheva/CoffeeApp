package com.example.coffeapp.Coffee.Model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
;
import java.time.LocalDateTime;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Long phoneNumber;
    @Enumerated(EnumType.STRING)
    City city;

    String street;

    String house;

    String porch;

    String apartment;
//    @Temporal(TemporalType.DATE)

    @DateTimeFormat(pattern = "hh:mm dd-MM-yyyy")
    @Column(name = "date_of_delivery", columnDefinition = "TIMESTAMP")
    LocalDateTime dateOfDelivery;

    public String getStringAddress() {
        String str;
        if (phoneNumber != null) {
            str = name + ", тел. " + phoneNumber + ", \n" +
                    city + ", " + street + ", дом " + house + ", подъезд " + porch + ", квартира " + apartment;

        } else {
            str = "";
        }
    return str;
    }

}
