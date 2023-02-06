package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Location;
import com.example.coffeapp.Coffee.Service.CoffeeService;
import com.example.coffeapp.Coffee.Service.LocationService;
import com.example.coffeapp.Coffee.Service.OrderService;
import com.example.coffeapp.Coffee.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/statistics/")
public class StatisticController {

    private final LocationService locationService;
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("admin-statistics")
    public String findAll(Model model) {
    model.addAttribute("usersquant", userService.findAll().size());
    model.addAttribute("ordersquant", orderService.findAll().size());
    model.addAttribute("locationsquant", locationService.findAll().size());
    List<String>locNames = new ArrayList<>();
    List<Integer>locValues = new ArrayList<>();
    for (Location location : locationService.findAll()) {
        System.out.println(location.getAddress());
        locNames.add(location.getAddress());
//        locValues.add(location.getOrderList().size());
    }
    model.addAttribute("locNames", locNames);
    model.addAttribute("locValues", locValues);
        return "/Admin/admin-statistics";
    }
}
