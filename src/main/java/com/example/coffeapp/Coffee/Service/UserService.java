package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;


    public User findById (Long id) { return userRepository.getReferenceById(id); }

    public List<User> findAll() { return userRepository.findAll(); }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<User> findAllById(Iterable<Long> idusers) {
        return userRepository.findAllById(idusers);
    }

    public User save(User user) { return userRepository.save(user); }

    public void deleteById(Long id) { userRepository.deleteById(id); }

    public User findByUserName(String username) { return userRepository.findByUsername(username); }

    }
