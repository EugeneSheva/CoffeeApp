package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditiveType;
import com.example.coffeapp.Coffee.Model.Order;
import com.example.coffeapp.Coffee.Model.OrderStatus;
import com.example.coffeapp.Coffee.Model.OrderedProduct;
import com.example.coffeapp.Coffee.Model.Product.Coffee;
import com.example.coffeapp.Coffee.Model.Product.Product;
import com.example.coffeapp.Coffee.Model.Users.User;
import com.example.coffeapp.Coffee.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;
    private final CoffeeService coffeeService;
    private final LocationService locationService;
    private final CoffeeAdditivesService coffeeAdditivesServiceService;
    private final CoffeeAdditivesService coffeeAdditivesService;

    @GetMapping("/admin-order")
    public String findOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("locationName", "Все локации");
        model.addAttribute("locations", locationService.findAll());
        System.out.println(locationService.findAll());
        return "/Admin/admin-orders";
    }

    @GetMapping("/admin-orders/{id}")
    public String findOrdersByLocationId(@PathVariable("id") Long id, Model model) {
        List<Order> orders = orderService.findOrdersByLocationId(id);
        model.addAttribute("orders", orders);
        model.addAttribute("locationName", locationService.findById(id).getAddress());
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("locationId", id);
        System.out.println(locationService.findAll());
        model.addAttribute("showButton", true);
        return "/Admin/admin-orders";
    }
    @GetMapping("/order-update/{id}")
    public String orderEdit(@PathVariable("id") Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);

        OrderedProduct orderedProduct = new OrderedProduct();
        model.addAttribute("orderedProduct", orderedProduct);

        List<Product>allProductList = productService.findAll();
        model.addAttribute("allProductList", allProductList);

        List<CoffeeAdditive> addList = coffeeAdditivesServiceService.findAll();
        List<CoffeeAdditive> syrup = new ArrayList<>();
        List<CoffeeAdditive> alcohol = new ArrayList<>();
        List<CoffeeAdditive> milk = new ArrayList<>();
        List<CoffeeAdditive> additives = new ArrayList<>();
        for (CoffeeAdditive coffeeAdditive : addList) {
            if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.SYRUP)) { syrup.add(coffeeAdditive);
            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.ALCOHOL)) { alcohol.add(coffeeAdditive);
            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.MILK)) { milk.add(coffeeAdditive);
            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.ADD)) { additives.add(coffeeAdditive);
            }
        }
        model.addAttribute("syrups", syrup);

        model.addAttribute("alcohols", alcohol);

        model.addAttribute("milks", milk);

        model.addAttribute("additives", additives);

        return "/Admin/order-card";
    }


    @GetMapping("/order-create/{id}")
    public String newOrderCreate(@PathVariable("id") Long id, Model model) {

        Order order = new Order();
        order.setLocation(locationService.findById(id));
        model.addAttribute("order", order);

        User user = new User();
        model.addAttribute("user", user);

        OrderedProduct orderedProduct = new OrderedProduct();
        model.addAttribute("orderedProduct", orderedProduct);

        List<Product>allProductList = productService.findAll();
        model.addAttribute("allProductList", allProductList);

        List<CoffeeAdditive> addList = coffeeAdditivesServiceService.findAll();
        List<CoffeeAdditive> syrup = new ArrayList<>();
        List<CoffeeAdditive> alcohol = new ArrayList<>();
        List<CoffeeAdditive> milk = new ArrayList<>();
        List<CoffeeAdditive> additives = new ArrayList<>();
        for (CoffeeAdditive coffeeAdditive : addList) {
            if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.SYRUP)) { syrup.add(coffeeAdditive);
            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.ALCOHOL)) { alcohol.add(coffeeAdditive);
            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.MILK)) { milk.add(coffeeAdditive);
            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.ADD)) { additives.add(coffeeAdditive);
            }
        }
        model.addAttribute("syrups", syrup);

        model.addAttribute("alcohols", alcohol);

        model.addAttribute("milks", milk);

        model.addAttribute("additives", additives);




        return "/Admin/order-card";
    }

    @GetMapping("/intermediate-order-create")
    public String intermediateOrderCreate(Model model, @ModelAttribute("order") Order order) {

        model.addAttribute("order", order);
        System.out.println("Заказ перед вью- " + order);

        OrderedProduct orderedProduct = new OrderedProduct();
        model.addAttribute("orderedProduct", orderedProduct);

        List<Product>allProductList = productService.findAll();
        model.addAttribute("allProductList", allProductList);

        List<CoffeeAdditive> addList = coffeeAdditivesServiceService.findAll();
        List<CoffeeAdditive> syrups = new ArrayList<>();
        List<CoffeeAdditive> alcohol = new ArrayList<>();
        List<CoffeeAdditive> milk = new ArrayList<>();
        List<CoffeeAdditive> additives = new ArrayList<>();
        for (CoffeeAdditive coffeeAdditive : addList) {
            if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.SYRUP)) { syrups.add(coffeeAdditive);
            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.ALCOHOL)) { alcohol.add(coffeeAdditive);
            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.MILK)) { milk.add(coffeeAdditive);
            } else if (coffeeAdditive.getCoffeeAdditiveType().equals(CoffeeAdditiveType.ADD)) { additives.add(coffeeAdditive);
            }
        }
        model.addAttribute("syrups", syrups);

        model.addAttribute("alcohols", alcohol);

        model.addAttribute("milks", milk);

        model.addAttribute("additives", additives);


        return "/Admin/order-card";
    }

    @GetMapping("/del-ordered-product/{id}")
    public String orderDelete(@PathVariable("id") Long id, Model model) {

        return "/Admin/order-card";
    }


    @RequestMapping(value = "/get-ordered-product-price", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody String sendPrice(@RequestParam(name = "product", defaultValue = "0") Long productId, @RequestParam(name = "quantity", defaultValue = "1") Long quantity, @RequestParam(name = "add1", defaultValue = "0") Long add1, @RequestParam(name = "add2", defaultValue = "0") Long add2, @RequestParam(name = "add3", defaultValue = "0") Long add3, @RequestParam(name = "add4", defaultValue = "0") Long add4,  @RequestParam(name = "size", defaultValue = "") String size) {
        System.out.println("get-ordered-product-price start");
        Double sum = 0d;
        String calc = "";
        Product product = productService.findById(productId);
        if (product.getType().equalsIgnoreCase("Coffee")) {
            Coffee coffee = coffeeService.findById(productId);
            Double sizePrice = 0d;
            List<CoffeeAdditive> CALIst = new ArrayList<>();
            List<Long> addIds = new ArrayList<>();
            if (add1 > 0) addIds.add(add1);
            if (add2 > 0) addIds.add(add2);
            if (add3 > 0) addIds.add(add3);
            if (add4 > 0) addIds.add(add4);
            System.out.println("size =" + addIds.size());
            System.out.println("ids = "+ addIds);
            if (addIds.size()>0) {
                CALIst = coffeeAdditivesService.findAllById(addIds);
                System.out.println(CALIst);
            }
            if (size.equalsIgnoreCase("S")) {
                sizePrice = coffee.getSPrice();
            } else if (size.equalsIgnoreCase("M")) {
                sizePrice = coffee.getMPrice();
            } else if (size.equalsIgnoreCase("L")) {
                sizePrice = coffee.getLPrice();
            }else if (size.equalsIgnoreCase("XL")) {
                sizePrice = coffee.getXlPrice();
            }
            sum = sizePrice;
            calc = sizePrice+" ";
            if (addIds.size()>0) {
                for (CoffeeAdditive coffeeAdditive : CALIst) {
                    sum += coffeeAdditive.getPrice();
                    calc += "+ " + coffeeAdditive.getPrice();
                }
            }
            sum = sum * quantity;
            calc += " X" + quantity + " = " + sum+"грн.";
        } else {
            sum += product.getSPrice() * quantity;
            calc += product.getSPrice() + " X " + quantity + " = " + sum+"грн.";
        }
        System.out.println("get-ordered-product-price fin");
        return calc;
    }

    @RequestMapping(value = "/check-ordered-product-type", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody String sendList(@RequestParam("id") Long id) {

        if (productService.findById(id).getType().equalsIgnoreCase("Coffee")) {
            Product product = productService.findById(id);
            System.out.println("product = " + product);
            String tmp = "<select class=\"form-select\" id=\"opSize\" name=\"opSize\" aria-label=\"Default select\" required>\n" +
                    "   <option value=\"\">Выберите размер</option>                                         ";
            for (String size : product.getSizes()) {
                if (size != null)
                tmp += "                                            <option value=\""+ size +"\">"+ size +"</option>\n";
            }
            tmp += "                                        </select>\n" +
                    "<label for=\"opSize\">Размер</label>" +
                    "<div class=\"invalid-feedback\">\n" +
                    "      Пожалуйста, выберите размер."+
                    "</div>";
            System.out.println(tmp);
            return  tmp;
        } else {
            System.out.println("notcoffee");
            return "notcoffee";

        }

    }




    @RequestMapping(value = "/add-ordered-product-to-list", method = {RequestMethod.POST, RequestMethod.PUT})
    public String addOrderedProduct(RedirectAttributes redirectAttrs, @ModelAttribute Order order, @ModelAttribute OrderedProduct orderedProduct,
                                     @RequestParam("orderStatus") String status, @RequestParam("username") String username, @RequestParam(name ="opSize", defaultValue = "") String opSize) throws IOException {
        System.out.println(username);
        if(order.getUser() == null) order.setUser(userService.findByUserName(username));
        System.out.println("2 " + order.getUser());
        orderedProduct.setSize(opSize);


        for (int i = 0; i < order.getOrderedProductList().size(); i++) {
            if (order.getOrderedProductList().get(i).getProduct() == null) order.getOrderedProductList().remove(i);
        }
        System.out.println(orderedProduct.getCoffeeAdditiveList().size());
        List<CoffeeAdditive>catmp = new ArrayList<>();

        for (int i = 0; i < orderedProduct.getCoffeeAdditiveList().size(); i++) {
            System.out.println(i + ") " + orderedProduct.getCoffeeAdditiveList().get(i));
            if (orderedProduct.getCoffeeAdditiveList().get(i) != null) catmp.add(orderedProduct.getCoffeeAdditiveList().get(i));
        }
        orderedProduct.setCoffeeAdditiveList(catmp);
//calc
        Double sum = 0d;
        Product product = orderedProduct.getProduct();
        if (product.getType().equalsIgnoreCase("Coffee")) {
            Coffee coffee = coffeeService.findById(product.getId());

            if (orderedProduct.getSize().equalsIgnoreCase("S")) {
                sum = coffee.getSPrice();
            } else if (orderedProduct.getSize().equalsIgnoreCase("M")) {
                sum = coffee.getMPrice();
            } else if (orderedProduct.getSize().equalsIgnoreCase("L")) {
                sum = coffee.getLPrice();
            }else if (orderedProduct.getSize().equalsIgnoreCase("XL")) {
                sum = coffee.getXlPrice();
            }

            if (orderedProduct.getCoffeeAdditiveList().size()>0) {
                for (CoffeeAdditive coffeeAdditive : orderedProduct.getCoffeeAdditiveList()) {
                    sum += coffeeAdditive.getPrice();
                }
            }
            sum = sum * orderedProduct.getQuantity();
        } else {
            sum += product.getSPrice() * orderedProduct.getQuantity();
        }
        orderedProduct.setPrice(sum);

        System.out.println(orderedProduct.getCoffeeAdditiveList().size());
        if (orderedProduct.getProduct() != null) order.addOrderedProductList(orderedProduct);

        if (status.equalsIgnoreCase("ORDERED")) {
        order.setOrderStatus(OrderStatus.ORDERED);
        } else if (status.equalsIgnoreCase("READY")) {
        order.setOrderStatus(OrderStatus.READY);
        } else if (status.equalsIgnoreCase("COMPLETED")) {
            order.setOrderStatus(OrderStatus.COMPLETED);
        }
        Double orderPrice = 0d;
        if (order.getOrderedProductList().size() > 0) {
            for (OrderedProduct orProduct : order.getOrderedProductList()) {
                orderPrice += orProduct.getPrice();
            }
        }
        order.setPrice(orderPrice);
        redirectAttrs.addFlashAttribute("order", order);
        return "redirect:/order/intermediate-order-create";
    }

    @RequestMapping(value = "/order-card-save", method = {RequestMethod.POST, RequestMethod.PUT})
    public String saveOrder(@ModelAttribute Order order, @ModelAttribute OrderedProduct orderedProduct,
                            @RequestParam("orderStatus") String status, @RequestParam("username") String username, @RequestParam(name ="opSize", defaultValue = "") String opSize) throws IOException {

        if(order.getUser() == null) order.setUser(userService.findByUserName(username));

        orderedProduct.setSize(opSize);

        for (int i = 0; i < order.getOrderedProductList().size(); i++) {
            if (order.getOrderedProductList().get(i).getProduct() == null) order.getOrderedProductList().remove(i);
        }
        System.out.println(orderedProduct.getCoffeeAdditiveList().size());
        List<CoffeeAdditive>catmp = new ArrayList<>();

        for (int i = 0; i < orderedProduct.getCoffeeAdditiveList().size(); i++) {
            System.out.println(i + ") " + orderedProduct.getCoffeeAdditiveList().get(i));
            if (orderedProduct.getCoffeeAdditiveList().get(i) != null) catmp.add(orderedProduct.getCoffeeAdditiveList().get(i));
        }
        orderedProduct.setCoffeeAdditiveList(catmp);
//calc
        if (orderedProduct.getProduct() != null) {
            Double sum = 0d;
            Product product = orderedProduct.getProduct();
            if (product.getType().equalsIgnoreCase("Coffee")) {
                Coffee coffee = coffeeService.findById(product.getId());

                if (orderedProduct.getSize().equalsIgnoreCase("S")) {
                    sum = coffee.getSPrice();
                } else if (orderedProduct.getSize().equalsIgnoreCase("M")) {
                    sum = coffee.getMPrice();
                } else if (orderedProduct.getSize().equalsIgnoreCase("L")) {
                    sum = coffee.getLPrice();
                } else if (orderedProduct.getSize().equalsIgnoreCase("XL")) {
                    sum = coffee.getXlPrice();
                }

                if (orderedProduct.getCoffeeAdditiveList().size() > 0) {
                    for (CoffeeAdditive coffeeAdditive : orderedProduct.getCoffeeAdditiveList()) {
                        sum += coffeeAdditive.getPrice();

                    }
                }
                sum = sum * orderedProduct.getQuantity();

            } else {
                sum += product.getSPrice() * orderedProduct.getQuantity();
            }
            orderedProduct.setPrice(sum);
        }

        System.out.println(orderedProduct.getCoffeeAdditiveList().size());
        if (orderedProduct.getProduct() != null) order.addOrderedProductList(orderedProduct);

        if (status.equalsIgnoreCase("ORDERED")) {
            order.setOrderStatus(OrderStatus.ORDERED);
        } else if (status.equalsIgnoreCase("READY")) {
            order.setOrderStatus(OrderStatus.READY);
        } else if (status.equalsIgnoreCase("COMPLETED")) {
            order.setOrderStatus(OrderStatus.COMPLETED);
        }
        Double orderPrice = 0d;
        if (order.getOrderedProductList().size() > 0) {
            for (OrderedProduct orProduct : order.getOrderedProductList()) {
                orderPrice += orProduct.getPrice();
            }
        }
        order.setPrice(orderPrice);

        orderService.save(order);
        return "redirect:/order/admin-orders/"+order.getLocation().getId();
    }
}
