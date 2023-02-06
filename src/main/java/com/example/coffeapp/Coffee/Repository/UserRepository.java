package com.example.coffeapp.Coffee.Repository;

import com.example.coffeapp.Coffee.Model.Users.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    @Override
    List<User> findAllById(Iterable<Long> idusers);

    User findByUsername(String username);


}
