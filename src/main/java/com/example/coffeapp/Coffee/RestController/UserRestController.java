package com.example.coffeapp.Coffee.RestController;

import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    @Autowired
        private UserService userService;



        @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
        public @ResponseBody List<User> getAll() {
            List<User>users = new ArrayList<>();
            if (userService.findAll() != null) users = userService.findAll();
            return users;
        }

}
