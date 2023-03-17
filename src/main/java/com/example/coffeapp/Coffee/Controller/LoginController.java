package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/hello")
    public String getAllMenuHome() {
        return "/Admin/Menu/admin-menu";
    }

    @GetMapping("/login")
    public String getLogin(Model model, @ModelAttribute String regComplete) {
        model.addAttribute("regComplete", regComplete);
        return "/login";
    }


    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute String regError ) {
        User user = new User();
        user.setLanguage("ukrainian");
        model.addAttribute("user", user);
        if (regError.length()>0) model.addAttribute("regError", regError);
        return "/registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                          @RequestParam(name="password", defaultValue ="") String password,
                          @RequestParam(name="repassword", defaultValue ="") String repassword, Model model) {
        if (bindingResult.hasErrors()) {
            return "/registration";
        } else {
            if (password.equals(repassword)) {
                User userFromDB = userService.findByUserName(user.getUsername());
                if (userFromDB != null) {
                    redirectAttributes.addFlashAttribute("regError", "Пользователь с таким именем существует.");
                    System.out.println("err");
                    return "redirect:/registration";
                } else {
                    user.setActive(true);
                    user.setRole("ROLE_USER");
                    user.setPassword(passwordEncoder.encode(password));
                    userService.save(user);
                    String regComplete = "Регистрация завершина.";
                    redirectAttributes.addFlashAttribute(regComplete);
                    return "redirect:/login";
                }

            } else {
                String message = "Поля пароль и повторите пароль не совпадают!";
                model.addAttribute("message", message);
                return "registration";
            }
        }
    }
}
