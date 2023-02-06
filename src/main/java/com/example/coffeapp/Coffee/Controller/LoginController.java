package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/hello")
    public String getAllMenuHome() {
        return "/Admin/Menu/admin-menu";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "/Admin/user-reg";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute User user, Model model) {
        User userFromDB = userService.findByUserName(user.getUsername());
        if (userFromDB != null) {
            model.addAttribute("message", "Пользователь существует.");
            return "/Admin/user-reg";
        }
        user.setActive(true);
        user.setRole("ROLE_USER");
        System.out.println(user);
        userService.save(user);
        return "redirect:/login";
    }
}
