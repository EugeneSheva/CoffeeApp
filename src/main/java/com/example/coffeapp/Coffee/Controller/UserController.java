package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Value("${upload.path}")
    private String uploadPath;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/admin-users")
    public String findAllUsers(Model model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        model.addAttribute("users", users);
        return "/Admin/admin-users";
    }

    @GetMapping("/user-create")
    public String regUser(Model model) {
        User user = new User();
        user.setActive(true);
        user.setLanguage("ukrainian");
        model.addAttribute("user", user);
        return "Admin/user-card";
    }


    @PostMapping("/user-save")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam(name = "id", defaultValue = "0") Long id,
                             @RequestParam(name = "role", defaultValue = "") String role, @RequestParam(name = "active", defaultValue = "") Boolean active,
                             @RequestParam(name = "password", defaultValue = "") String password, @RequestParam(name = "repassword", defaultValue = "") String repassword, Model model) {
        if (bindingResult.hasErrors()) {
            return "Admin/user-card";
        } else {
            System.out.println("bindingResult.hasErrors() " + bindingResult.hasErrors());
            User userFromDB = userService.findByUserName(user.getUsername());
            if (userFromDB != null) {
                System.out.println("err");
                String message = "Пользователь с таким именем существует.";
                model.addAttribute("message", message);
                return "Admin/user-card";
            }
            if (password.equals(repassword)) {
                if (role.length() == 0) {
                    user.setRole("ROLE_USER");
                    user.setActive(true);
                }
                user.setPassword(passwordEncoder.encode(password));
                userService.save(user);
                return "redirect:/user/admin-users";
            } else {
                String message = "Поля пароль и повторите пароль не совпадают!";
                model.addAttribute("message", message);
                return "Admin/user-card";
            }
        }
    }


    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/user/admin-users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "Admin/user-card-edit";
    }

    @PostMapping("/user-save-edit")
    public String saveUpdateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam(name = "id", defaultValue = "0") Long id,
                                 @RequestParam(name = "role", defaultValue = "") String role, @RequestParam(name = "active", defaultValue = "true") Boolean active,
                                 @RequestParam(name = "password", defaultValue = "") String password, @RequestParam(name = "oldPassword", defaultValue = "") String oldPassword,
                                 @RequestParam(name = "newPassword", defaultValue = "") String newPassword, @RequestParam(name = "newRePassword", defaultValue = "") String newRePassword, Model model) {
        if (bindingResult.hasErrors()) {
            return "Admin/user-card-edit";
        } else {
            User oldUser = userService.findById(id);
            if (oldUser.getUsername().equals(user.getUsername())) {
            } else {
                User userFromDB = userService.findByUserName(user.getUsername());
                if (userFromDB != null) {
                    String message = "Пользователь с таким именем существует.";
                    model.addAttribute("message", message);
                    return "Admin/user-card-edit";
                }
            }
            if (oldPassword.length() > 0 || newPassword.length() > 0 || newRePassword.length() > 0) {
                if (passwordEncoder.matches(oldPassword, oldUser.getPassword())) {
                    if (newPassword.equals(newRePassword)) {
                        user.setPassword(passwordEncoder.encode(newPassword));
                    } else {
                        String message = "Поле новый пароль и повторите новый пароль не совпадают!";
                        model.addAttribute("message", message);
                        return "Admin/user-card-edit";
                    }
                } else {
                    String message = "Старый пароль не верный!";
                    model.addAttribute("message", message);
                    return "Admin/user-card-edit";
                }
            }
            if (role.length() == 0) {
                user.setRole(oldUser.getRole());
                user.setActive(oldUser.isActive());
            }
            userService.save(user);
            return "redirect:/user/admin-users";
        }

    }
}
