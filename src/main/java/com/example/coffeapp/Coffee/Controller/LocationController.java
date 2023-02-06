package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Location;
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
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {
    @Value("${upload.path}")


    private String uploadPath;
    private final LocationService locationService;



    @GetMapping("/admin-locations")
    public String getLocationList(Model model) {
        List<Location> locationList = locationService.findAll();
        model.addAttribute("location", locationList);
        return "/Admin/admin-locations";
    }


    @GetMapping("/location-update/{id}")
    public String coffeeEdit(@PathVariable("id") Long id, Model model) {
        Location location = locationService.findById(id);
        model.addAttribute("location", location);
        return "/Admin/location-card";
    }


    @GetMapping("/location-create")
    public String newCoffeeCreate(Model model) {
        Location location = new Location();
        model.addAttribute("location", location);
        return "/Admin/location-card";
    }


    @GetMapping("/location-delete/{id}")
    public String delleteCoffee(@PathVariable("id") Long id) {
        Location obj = locationService.findById(id);
        try {
            Files.deleteIfExists(Path.of(uploadPath + obj.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        locationService.deleteById(id);
        return "redirect:/location/admin-locations";
    }


    @PostMapping("/location-card-save")
    public String saveCoffee(@RequestParam(name = "id", defaultValue = "0") Long id, @RequestParam("address") String address, @RequestParam("description") String description,
                             @RequestParam("phoneNunber") Integer phoneNunber,  @RequestParam("coordinates") Integer coordinates, @RequestParam("image") MultipartFile file) throws IOException {

        Location location = new Location();
        Location oldLocation = new Location();

        if (id>0) { location.setId(id);
            oldLocation = locationService.findById(id);}

        location.setAddress(address);
        location.setPhoneNunber(phoneNunber);
        location.setCoordinates(coordinates);
        location.setDescription(description);

        //            save image

        if (!(Files.exists(Path.of(uploadPath + "/img/location/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/location/"));

        if (file.getSize() > 0) {
            if (id>0) Files.deleteIfExists(Paths.get(uploadPath + oldLocation.getImage()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/img/location/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            location.setImage("/img/location/" + FileNameUuid);
        } else {
            if (oldLocation.getImage() != null)
                location.setImage(oldLocation.getImage());
        }

        locationService.save(location);

        return "redirect:/location/admin-locations";
    }

}
