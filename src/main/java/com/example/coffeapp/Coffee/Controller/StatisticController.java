package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Location;
import com.example.coffeapp.Coffee.Model.Order;
import com.example.coffeapp.Coffee.Service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

@Controller
@RequiredArgsConstructor
@RequestMapping("/statistics/")
@Log4j2
public class StatisticController {

    private final LocationService locationService;
    private final UserService userService;
    private final OrderService orderService;
    private final StatisticService statisticService;


    @GetMapping("admin-statistics")
    public String statistics(Model model) {
        log.warn("Test");
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


    @PostMapping("admin-statistics-bydate")
    public String statisticsByDate(Model model, @RequestParam(name = "dateFrom", defaultValue = "") String From, @RequestParam(name = "dateTo", defaultValue = "") String To) throws ParseException {
        Date dateFrom;
        Date dateTo;
        String filter;
        if (From.length()==0 && To.length()==0) {
           return statistics(model);
       } else if (From.length()==0) {
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            dateFrom = formatter.parse("2021-01-01");
            dateTo = formatter.parse(To);
            filter = "Выборка по дате: с 2021-01-01 по "+ To+".";
       } else if (To.length()==0) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateFrom = formatter.parse(From);
            dateTo = new Date();
            filter = "Выборка по дате: с "+From+" по сегодняшний день.";
        } else {
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            dateFrom = formatter.parse(From);
            dateTo = formatter.parse(To);
            filter = "Выборка по дате: с "+From+" по "+ To+".";
        }

        model.addAttribute("filter", filter);
        model.addAttribute("usersquant", userService.findUsersByDateOfRegistryBetween(dateFrom, dateTo).size());
        model.addAttribute("ordersquant", orderService.findOrdersByDateOfOrderBetween(dateFrom, dateTo).size());
        model.addAttribute("locationsquant", locationService.findAll().size());
        Double revenue = 0d;
        for (Order order : orderService.findOrdersByDateOfOrderBetween(dateFrom, dateTo)) {
            revenue += order.getPrice();
        }
        model.addAttribute("revenuequant", revenue);

        model.addAttribute("locationnames", statisticService.LocationNamesListToJS());
        model.addAttribute("locationorders", statisticService.LocationOrdersListByDateToJS(dateFrom, dateTo));
        model.addAttribute("locationrevenue", statisticService.LocationRevenueListByDateToJS(dateFrom, dateTo));

        String productnames ="";
        String productquantity ="";
        int i = 1;
        for (String s : statisticService.getProductRatingByDate(dateFrom, dateTo)) {
            String[]arr = s.split(",");
            productnames += arr[0];
            productquantity+=arr[1];
            if (i++ < statisticService.getProductRatingByDate(dateFrom, dateTo).size()) {
                productnames += "*";
                productquantity += "*";
            }
        }
        model.addAttribute("productnames", productnames);
        model.addAttribute("productquantity", productquantity);

        return "/Admin/admin-statistics";
    }
}
