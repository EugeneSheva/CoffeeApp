package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Location;
import com.example.coffeapp.Coffee.Model.Order;
import com.example.coffeapp.Coffee.Service.LocationService;
import com.example.coffeapp.Coffee.Service.OrderService;
import com.example.coffeapp.Coffee.Service.StatisticService;
import com.example.coffeapp.Coffee.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final LocationService locationService;
    private final UserService userService;
    private final OrderService orderService;
    private final StatisticService statisticService;


    @GetMapping("admin-statistics")
    public String statistics(Model model) {
        model.addAttribute("filter", "Выборка по дате: за все время.");
        model.addAttribute("usersquant", userService.findAll().size());
        model.addAttribute("ordersquant", orderService.findAll().size());
        model.addAttribute("locationsquant", locationService.findAll().size());
        Double revenue = 0d;
        for (Order order : orderService.findAll()) {
            revenue += order.getPrice();
        }
        model.addAttribute("revenuequant", revenue);
        model.addAttribute("locationnames", statisticService.LocationNamesListToJS());
        model.addAttribute("locationorders", statisticService.LocationOrdersListToJS());
        model.addAttribute("locationrevenue", statisticService.LocationRevenueListToJS());

        String productnames ="";
        String productquantity ="";
        int i = 1;
        for (String s : statisticService.getProductRating()) {
            String[]arr = s.split(",");
            productnames += arr[0];
            productquantity+=arr[1];
            if (i++ < statisticService.getProductRating().size()) {
                productnames += "*";
                productquantity += "*";
            }
        }
        model.addAttribute("productnames", productnames);
        model.addAttribute("productquantity", productquantity);
        return "/Admin/admin-statistics";
    }


}
