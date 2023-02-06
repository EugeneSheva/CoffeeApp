package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditive;
import com.example.coffeapp.Coffee.Model.Additives.CoffeeAdditiveType;
import com.example.coffeapp.Coffee.Model.Product.*;
import com.example.coffeapp.Coffee.Service.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    @Value("${upload.path}")
    private String uploadPath;
    private File uploadDir;

    private final CoffeeService coffeeService;
    private final TeaService teaService;
    private final SnackService snackService;
    private final DessertService dessertService;
    private final SandwichService sandwichService;
    private final CoffeeAdditivesService coffeeAdditivesService;
    @GetMapping("/admin-menu")
    public String getAllMenu() {
        return "/Admin/Menu/admin-menu";
    }


    //Coffee


    @GetMapping("/coffee-list")
    public String getCoffeeList(Model model) {
        List<Coffee>productList = coffeeService.findAll();
        model.addAttribute("product", productList);
        return "/Admin/Menu/coffee-list";
    }
    @GetMapping("/coffee-update/{id}")
    public String coffeeEdit(@PathVariable("id") Long id, Model model) {
        Coffee coffee = coffeeService.findById(id);
        model.addAttribute("product", coffee);
        return "/Admin/Menu/coffee-card";
    }
    @GetMapping("/coffee-create")
    public String newCoffeeCreate(Model model) {
        Coffee coffee = new Coffee();
        model.addAttribute("product", coffee);
        return "/Admin/Menu/coffee-card";
    }
    @GetMapping("/coffee-delete/{id}")
    public String delleteCoffee(@PathVariable("id") Long id) {
        Coffee obj = coffeeService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        coffeeService.deleteById(id);
        return "redirect:/menu/coffee-list";
    }

    @PostMapping("/coffee-card-save")
    public String saveCoffee(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                             @RequestParam("sValue") String sValue, @RequestParam("sPrice") Double sPrice, @RequestParam("mValue") String mValue, @RequestParam("mPrice") Double mPrice,
                             @RequestParam("lValue") String lValue, @RequestParam("lPrice") Double lPrice,@RequestParam("xlValue") String xlValue, @RequestParam("xlPrice") Double xlPrice,
                             @RequestParam("image") MultipartFile file) throws IOException {
        Coffee coffee = new Coffee();
        Coffee oldCoffee = new Coffee();
        if (id>0) { coffee.setId(id);
            oldCoffee = coffeeService.findById(id);}
        coffee.setName(name);
        coffee.setDescription(description);
        coffee.setSValue(sValue);
        coffee.setSPrice(sPrice);
        coffee.setMValue(mValue);
        coffee.setMPrice(mPrice);
        coffee.setLValue(lValue);
        coffee.setLPrice(lPrice);
        coffee.setXlValue(xlValue);
        coffee.setXlPrice(xlPrice);
        String[] tmp = new String[4];
        int i=0;
        if (sPrice>0 && sPrice!=null && sValue!=null)
            tmp[i] = "S"; i++;
        if (mPrice>0 && mPrice!=null && mValue!=null)
            tmp[i] = "M"; i++;
        if (lPrice>0 && lPrice!=null && lValue!=null)
            tmp[i] = "L"; i++;
        if (xlPrice>0 && xlPrice!=null && xlValue!=null)
            tmp[i] = "XL"; i++;

        coffee.setSizes(tmp);

        //            сохранение  фото
        if (!(Files.exists(Path.of(uploadPath + "/img/coffee/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/coffee/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldCoffee.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/coffee/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            coffee.setImage("/img/coffee/" + FileNameUuid);
        } else {
            if (oldCoffee.getImage() != null)
                coffee.setImage(oldCoffee.getImage());
        }

        coffeeService.save(coffee);

        return "redirect:/menu/coffee-list";
    }

    //Tea

    @GetMapping("/tea-list")
    public String getTeaList(Model model) {
        List<Tea>productList = teaService.findAll();
            model.addAttribute("product", productList);
        return "/Admin/Menu/tea-list";
    }
    @GetMapping("/tea-update/{id}")
    public String teaEdit(@PathVariable("id") Long id, Model model) {
        Tea tea = teaService.findById(id);
        model.addAttribute("product", tea);
        return "/Admin/Menu/tea-card";
    }
    @GetMapping("/tea-create")
    public String newTeaCreate(Model model) {
        Tea tea = new Tea();
        model.addAttribute("product", tea);
        return "/Admin/Menu/tea-card";
    }
    @GetMapping("/tea-delete/{id}")
    public String delleteTea(@PathVariable("id") Long id) {
        Tea obj = teaService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       teaService.deleteById(id);
        return "redirect:/menu/tea-list";
    }

    @PostMapping("/tea-card-save")
    public String saveTea(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                          @RequestParam("sValue") String sValue, @RequestParam("sPrice") Double sPrice, @RequestParam("image") MultipartFile file) throws IOException {
        Tea tea = new Tea();
        Tea oldTea = new Tea();
        if (id>0) { tea.setId(id);
            oldTea = teaService.findById(id);}
        tea.setName(name);
        tea.setDescription(description);
        tea.setSValue(sValue);
        tea.setSPrice(sPrice);
        //            сохранение  фото
        if (!(Files.exists(Path.of(uploadPath + "/img/tea/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/tea/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldTea.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/tea/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tea.setImage("/img/tea/" + FileNameUuid);
        } else {
            if (oldTea.getImage() != null)
                tea.setImage(oldTea.getImage());
        }

        teaService.save(tea);

        return "redirect:/menu/tea-list";
    }

//   Snack

    @GetMapping("/snack-list")
    public String getSnackList(Model model) {
        List<Snack>productList = snackService.findAll();
        model.addAttribute("product", productList);
        return "/Admin/Menu/snack-list";
    }
    @GetMapping("/snack-update/{id}")
    public String snackEdit(@PathVariable("id") Long id, Model model) {
        Snack snack = snackService.findById(id);
        model.addAttribute("product", snack);
        return "/Admin/Menu/snack-card";
    }
    @GetMapping("/snack-create")
    public String newSnackCreate(Model model) {
        Snack snack = new Snack();
        model.addAttribute("product", snack);
        return "/Admin/Menu/snack-card";
    }
    @GetMapping("/snack-delete/{id}")
    public String delleteSnack(@PathVariable("id") Long id) {
        Snack obj = snackService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        snackService.deleteById(id);
        return "redirect:/menu/snack-list";
    }

    @PostMapping("/snack-card-save")
    public String saveSnack(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                            @RequestParam("sValue") String sValue, @RequestParam("sPrice") Double sPrice, @RequestParam("image") MultipartFile file) throws IOException {
        Snack snack = new Snack();
        Snack oldSnack = new Snack();
        if (id>0) { snack.setId(id);
            oldSnack = snackService.findById(id);}
        snack.setName(name);
        snack.setDescription(description);
        snack.setSValue(sValue);
        snack.setSPrice(sPrice);
        //            сохранение  фото
        if (!(Files.exists(Path.of(uploadPath + "/img/snack/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/snack/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldSnack.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/snack/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            snack.setImage("/img/snack/" + FileNameUuid);
        } else {
            if (oldSnack.getImage() != null)
                snack.setImage(oldSnack.getImage());
        }

        snackService.save(snack);

        return "redirect:/menu/snack-list";
    }

//   dessert

    @GetMapping("/dessert-list")
    public String getDessertList(Model model) {
        List<Dessert>productList = dessertService.findAll();
        model.addAttribute("product", productList);
        return "/Admin/Menu/dessert-list";
    }
    @GetMapping("/dessert-update/{id}")
    public String dessertEdit(@PathVariable("id") Long id, Model model) {
        Dessert dessert = dessertService.findById(id);
        model.addAttribute("product", dessert);
        return "/Admin/Menu/dessert-card";
    }
    @GetMapping("/dessert-create")
    public String newDessertCreate(Model model) {
        Dessert dessert = new Dessert();
        model.addAttribute("product", dessert);
        return "/Admin/Menu/dessert-card";
    }
    @GetMapping("/dessert-delete/{id}")
    public String delleteDessert(@PathVariable("id") Long id) {
        Dessert obj = dessertService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dessertService.deleteById(id);
        return "redirect:/menu/dessert-list";
    }

    @PostMapping("/dessert-card-save")
    public String saveDessert(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                              @RequestParam("sValue") String sValue, @RequestParam("sPrice") Double sPrice, @RequestParam("image") MultipartFile file) throws IOException {
        Dessert dessert = new Dessert();
        Dessert oldDessert = new Dessert();
        if (id>0) { dessert.setId(id);
            oldDessert = dessertService.findById(id);}
        dessert.setName(name);
        dessert.setDescription(description);
        dessert.setSValue(sValue);
        dessert.setSPrice(sPrice);
        //            сохранение  фото
        if (!(Files.exists(Path.of(uploadPath + "/img/dessert/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/dessert/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldDessert.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/dessert/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            dessert.setImage("/img/dessert/" + FileNameUuid);
        } else {
            if (oldDessert.getImage() != null)
                dessert.setImage(oldDessert.getImage());
        }

        dessertService.save(dessert);

        return "redirect:/menu/dessert-list";
    }

    //   sandwich

    @GetMapping("/sandwich-list")
    public String getSandwichList(Model model) {
        List<Sandwich>productList = sandwichService.findAll();
        model.addAttribute("product", productList);
        return "/Admin/Menu/sandwich-list";
    }
    @GetMapping("/sandwich-update/{id}")
    public String sandwichEdit(@PathVariable("id") Long id, Model model) {
        Sandwich sandwich = sandwichService.findById(id);
        model.addAttribute("product", sandwich);
        return "/Admin/Menu/sandwich-card";
    }
    @GetMapping("/sandwich-create")
    public String newSandwichCreate(Model model) {
        Sandwich sandwich = new Sandwich();
        model.addAttribute("product", sandwich);
        return "/Admin/Menu/sandwich-card";
    }
    @GetMapping("/sandwich-delete/{id}")
    public String delleteSandwich(@PathVariable("id") Long id) {
        Sandwich obj = sandwichService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sandwichService.deleteById(id);
        return "redirect:/menu/sandwich-list";
    }

    @PostMapping("/sandwich-card-save")
    public String saveSandwich(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("name") String name, @RequestParam("description") String description,
                               @RequestParam("sValue") String sValue, @RequestParam("sPrice") Double sPrice, @RequestParam("image") MultipartFile file) throws IOException {
        Sandwich sandwich = new Sandwich();
        Sandwich oldSandwich = new Sandwich();
        if (id>0) { sandwich.setId(id);
            oldSandwich = sandwichService.findById(id);}
        sandwich.setName(name);
        sandwich.setDescription(description);
        sandwich.setSValue(sValue);
        sandwich.setSPrice(sPrice);
        //            сохранение  фото
        if (!(Files.exists(Path.of(uploadPath + "/img/sandwich/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/sandwich/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldSandwich.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/sandwich/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sandwich.setImage("/img/sandwich/" + FileNameUuid);
        } else {
            if (oldSandwich.getImage() != null)
                sandwich.setImage(oldSandwich.getImage());
        }
        sandwichService.save(sandwich);

        return "redirect:/menu/sandwich-list";
    }


    @GetMapping("/admin-menu-additives/{id}")
    public String additivesList(@PathVariable("id") String id, Model model) {
        System.out.println(id);
        List<CoffeeAdditive> coffeeAdditiveList = coffeeAdditivesService.findAll();
        if (id.equalsIgnoreCase("SYRUP")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                            filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.SYRUP)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "SYRUP");
        } else if (id.equalsIgnoreCase("ALCOHOL")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.ALCOHOL)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "ALCOHOL");
        } else if (id.equalsIgnoreCase("MILK")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.MILK)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "MILK");
        } else if (id.equalsIgnoreCase("ADD")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.ADD)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "ADD");
        }
        return "/Admin/Menu/additives-list";
    }
    @GetMapping("/add-create/{id}")
    public String newAddCreate(@PathVariable("id") String addType, Model model) {

        List<CoffeeAdditive> coffeeAdditiveList = coffeeAdditivesService.findAll();
        if (addType.equalsIgnoreCase("SYRUP")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.SYRUP)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "SYRUP");
        } else if (addType.equalsIgnoreCase("ALCOHOL")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.ALCOHOL)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "ALCOHOL");
        } else if (addType.equalsIgnoreCase("MILK")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.MILK)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "MILK");
        } else if (addType.equalsIgnoreCase("ADD")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.ADD)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "ADD");
        }

        CoffeeAdditive coffeeAdditive = new CoffeeAdditive();

        model.addAttribute("coffeeAdditive", coffeeAdditive);
        return "/Admin/Menu/additives-card";
    }

    @PostMapping("/add-save")
    public String saveAdd(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam(name = "name", defaultValue = "") String name, @RequestParam(name = "price", defaultValue = "") Double price, @RequestParam(name = "coffeeAdditiveType", defaultValue = "") String addType) throws IOException {
        CoffeeAdditive coffeeAdditive = new CoffeeAdditive();
        if (id>0) coffeeAdditive.setId(id);
        coffeeAdditive.setName(name);
        coffeeAdditive.setPrice(price);
        if (addType.equalsIgnoreCase("SYRUP")) {
            coffeeAdditive.setCoffeeAdditiveType(CoffeeAdditiveType.SYRUP);
        } else if (addType.equalsIgnoreCase("ALCOHOL")) {
            coffeeAdditive.setCoffeeAdditiveType(CoffeeAdditiveType.ALCOHOL);
        } else if (addType.equalsIgnoreCase("MILK")) {
            coffeeAdditive.setCoffeeAdditiveType(CoffeeAdditiveType.MILK);
        } else if (addType.equalsIgnoreCase("ADD")) {
            coffeeAdditive.setCoffeeAdditiveType(CoffeeAdditiveType.ADD);
        }
        coffeeAdditivesService.save(coffeeAdditive);
        return "redirect:/menu/admin-menu-additives/"+coffeeAdditive.getCoffeeAdditiveType();
    }
    @GetMapping("/add-update/{id}")
    public String addEdit(@PathVariable("id") Long id, Model model) {
        CoffeeAdditive coffeeAdd = coffeeAdditivesService.findById(id);
        model.addAttribute("coffeeAdditive", coffeeAdd);
        String addType = coffeeAdd.getCoffeeAdditiveType().name();
        List<CoffeeAdditive> coffeeAdditiveList = coffeeAdditivesService.findAll();
        if (addType.equalsIgnoreCase("SYRUP")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.SYRUP)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "SYRUP");
        } else if (addType.equalsIgnoreCase("ALCOHOL")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.ALCOHOL)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "ALCOHOL");
        } else if (addType.equalsIgnoreCase("MILK")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.MILK)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "MILK");
        } else if (addType.equalsIgnoreCase("ADD")) {
            List<CoffeeAdditive>productList = coffeeAdditiveList.stream().
                    filter(coffeeAdditive -> coffeeAdditive.getCoffeeAdditiveType().
                            equals(CoffeeAdditiveType.ADD)).collect(Collectors.toList());
            System.out.println(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("type", "ADD");
        }


        return "/Admin/Menu/additives-card";
    }
    @GetMapping("/add-delete/{id}")
    public String delleteAdd(@PathVariable("id") Long id) {
        String tmp = coffeeAdditivesService.findById(id).getCoffeeAdditiveType().name();
        coffeeAdditivesService.deleteById(id);
        return "redirect:/menu/admin-menu-additives/"+tmp;
    }
}
