package com.example.coffeapp.Coffee.Repository;

import com.example.coffeapp.Coffee.Model.Pages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagesRepository extends JpaRepository<Pages, Long> {
    }
