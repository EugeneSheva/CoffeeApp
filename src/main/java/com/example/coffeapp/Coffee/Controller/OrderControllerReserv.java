//package com.example.coffeapp.Coffee.Controller;
//
//import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
//import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditiveType;
//import com.example.coffeapp.Coffee.Model.Order;
//import com.example.coffeapp.Coffee.Model.OrderStatus;
//import com.example.coffeapp.Coffee.Model.OrderedProduct;
//import com.example.coffeapp.Coffee.Model.OrderedProductListDTO;
//import com.example.coffeapp.Coffee.Model.Product.Product;
//import com.example.coffeapp.Coffee.Service.CoffeeAdditivesService;
//import com.example.coffeapp.Coffee.Service.CoffeeService;
//import com.example.coffeapp.Coffee.Service.OrderService;
//import com.example.coffeapp.Coffee.Service.ProductService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.persistence.EntityManager;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Controller
//@RequiredArgsConstructor
//public class OrderControllerReserv {
//    private final EntityManager entityManager;
//    private final OrderService orderService;
//    private final ProductService productService;
//    private final CoffeeService coffeeService;
//    private final CoffeeAdditivesService coffeeAdditivesServiceService;
//
//    @GetMapping("/admin-orders")
//    public String findAll(Model model) {
//        List<Order> orders = orderService.findAll();
//        model.addAttribute("orders", orders);
//        return "/Admin/admin-orders";
//    }
////    @GetMapping("order-update/{id}")
////    public String orderEdit(@PathVariable("id") Long id, Model model) {
////        Order order = orderService.findById(id);
////        model.addAttribute("order", order);
////        List<Product>allProductList = productService.findAll();
////        model.addAttribute("allProductList", allProductList);
////        return "/Admin/order-card";
////    }
//
//
//    @GetMapping("order-create")
//    public String newOrderCreate(Model model) {
//        Order order = new Order();
//        model.addAttribute("order", order);
//
//        OrderedProduct orderedProduct = new OrderedProduct();
//        model.addAttribute("orderedProduct", orderedProduct);
//
//        List<OrderedProduct>orderedProductList = new ArrayList<>();
//        OrderedProductListDTO orderedProductListDTO = new OrderedProductListDTO();
//        orderedProductListDTO.setOrderedProductList(orderedProductList);
//        model.addAttribute("orderedProductListDTO", orderedProductListDTO);
//        System.out.println("orderedProductListDTO" +orderedProductListDTO);
//
//        List<Product>allProductList = productService.findAll();
//        model.addAttribute("allProductList", allProductList);
//
//        List<CoffeeAdditive> addList = coffeeAdditivesServiceService.findAll();
//        List<CoffeeAdditive> syrups = new ArrayList<>();
//        List<CoffeeAdditive> alcohol = new ArrayList<>();
//        List<CoffeeAdditive> milk = new ArrayList<>();
//        List<CoffeeAdditive> additives = new ArrayList<>();
//        for (CoffeeAdditive coffeeAdditive : addList) {
//            if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.SYRUPS)) { syrups.add(coffeeAdditive);
//            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.ALCOHOL)) { alcohol.add(coffeeAdditive);
//            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.MILK)) { milk.add(coffeeAdditive);
//            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.ADD)) { additives.add(coffeeAdditive);
//            }
//        }
//        model.addAttribute("syrups", syrups);
//
//        model.addAttribute("alcohols", alcohol);
//
//        model.addAttribute("milks", milk);
//
//        model.addAttribute("additives", additives);
//
//        model.addAttribute("test", "test_message");
//        return "/Admin/order-card";
//    }
//
//    @GetMapping("intermediate-order-create")
//    public String intermediateOrderCreate(Model model, @ModelAttribute("order") Order order) {
//
//        model.addAttribute("order", order);
//        System.out.println("Заказ перед вью- " + order);
//
//        OrderedProduct orderedProduct = new OrderedProduct();
//        model.addAttribute("orderedProduct", orderedProduct);
//
//        List<OrderedProduct>orderedProductList = order.getOrderedProductList();
//        OrderedProductListDTO orderedProductListDTO = new OrderedProductListDTO();
//        orderedProductListDTO.setOrderedProductList(orderedProductList);
//        model.addAttribute("orderedProductListDTO", orderedProductListDTO);
//
//        List<Product>allProductList = productService.findAll();
//        model.addAttribute("allProductList", allProductList);
//
//        List<CoffeeAdditive> addList = coffeeAdditivesServiceService.findAll();
//        List<CoffeeAdditive> syrups = new ArrayList<>();
//        List<CoffeeAdditive> alcohol = new ArrayList<>();
//        List<CoffeeAdditive> milk = new ArrayList<>();
//        List<CoffeeAdditive> additives = new ArrayList<>();
//        for (CoffeeAdditive coffeeAdditive : addList) {
//            if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.SYRUPS)) { syrups.add(coffeeAdditive);
//            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.ALCOHOL)) { alcohol.add(coffeeAdditive);
//            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.MILK)) { milk.add(coffeeAdditive);
//            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.ADD)) { additives.add(coffeeAdditive);
//            }
//        }
//        model.addAttribute("syrups", syrups);
//
//        model.addAttribute("alcohols", alcohol);
//
//        model.addAttribute("milks", milk);
//
//        model.addAttribute("additives", additives);
//
//        model.addAttribute("test", "test_message");
//        return "/Admin/order-card";
//    }
//
//
//
////    @PostMapping("/admin-hall-card-add")
////    public String createHall(RedirectAttributes redirectAttrs) {
////        redirectAttrs.addFlashAttribute("hall", hall);
////    }
//
//
//
//
//    @RequestMapping(value = "/check-ordered-product-type", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
//    public @ResponseBody String sendList(@RequestParam("id") Long id) {
//        if (productService.getType(id).equalsIgnoreCase("Coffee")) {
//            return  "coffee";
//        } else {
//            return " ";
//        }
//    }
//
//    @PostMapping("/add-ordered-product-to-list")
//    public String addOrderedProduct(RedirectAttributes redirectAttrs, @ModelAttribute Order order, @ModelAttribute OrderedProduct orderedProduct,
//                                    @ModelAttribute OrderedProductListDTO orderedProductListDTO, @RequestParam("orderStatus") String status) throws IOException {
//        System.out.println("Заказ до - "+ order);
//        System.out.println("Заказанный продукт"+orderedProduct);
//        System.out.println("DTOorderedProductList = "+ orderedProductListDTO.getOrderedProductList());
//        if (orderedProductListDTO.getOrderedProductList() != null ) {
//            order.setOrderedProductList(orderedProductListDTO.getOrderedProductList());
//            order.addOrderedProductList(orderedProduct);
//        } else { order.setOrderedProductList(new ArrayList<>());
//        order.addOrderedProductList(orderedProduct);}
//        System.out.println("order.orderedProductList = "+ order.getOrderedProductList());
//        System.out.println("Заказ после - "+order);
//
//        if (status.equalsIgnoreCase("ORDERED")) {
//        order.setOrderStatus(OrderStatus.ORDERED);
//        } else if (status.equalsIgnoreCase("READY")) {
//        order.setOrderStatus(OrderStatus.READY);
//        } else if (status.equalsIgnoreCase("COMPLETED")) {
//            order.setOrderStatus(OrderStatus.COMPLETED);
//        }
//        redirectAttrs.addFlashAttribute("order", order);
//        return "redirect:/intermediate-order-create";
//    }
//
//
////    @RequestMapping(value = "/add-ordered-product", method = RequestMethod.POST )
////    public @ResponseBody String addList(@RequestParam("orList") List orList, Model model) {
////        orList.add(new OrderedProduct());
////        orList.add(new OrderedProduct());
////        System.out.println(orList);
////        model.addAttribute("orderedProductList", orList);
////        return " ";
////
////    }
//
//
//
//
//
//
//}
