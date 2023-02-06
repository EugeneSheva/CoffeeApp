package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Value("${upload.path}")
    private String uploadPath;

    private final UserService userService;

    @GetMapping("/admin-users")
        public String findAllUsers(Model model,@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 10)Pageable pageable) {
            Page<User> users = userService.findAll(pageable);
            model.addAttribute("users", users);
        return "/Admin/admin-users";
    }

    @GetMapping("/user-create")
    public String regUser(Model model) {
        model.addAttribute("user", new User());
        return "Admin/user-card";
    }


    @PostMapping("/user-save")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/user/admin-users";
    }


    //
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/user/admin-users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "Admin/user-card";
    }


}
