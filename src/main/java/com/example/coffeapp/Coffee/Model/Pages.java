package com.example.coffeapp.Coffee.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class Pages {
    @Id
    Long id;

    String mainImage1;
    String mainImage2;
    String mainImage3;

    String imageP1;
    String titleP1;
    String textP1;

    String imageP2;
    String titleP2;
    String textP2;

    String imageP3;
    String titleP3;
    String textP3;

    String imageP4;
    String titleP4;
    String textP4;

    public Pages() {
    }

    public Pages(Long id) {
        this.id = id;
    }
}
