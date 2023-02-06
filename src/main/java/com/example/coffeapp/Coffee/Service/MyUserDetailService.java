package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.example.coffeapp.Coffee.Model.Users.User dbUser = userRepository.findByUsername(userName);
        System.out.println("my user: "+dbUser);
        System.out.println("security usrr: "+ new User(dbUser.getUsername(), dbUser.getPassword(), dbUser.getRolesList()));
        return new User(dbUser.getUsername(), dbUser.getPassword(), dbUser.getRolesList());
    }
}
