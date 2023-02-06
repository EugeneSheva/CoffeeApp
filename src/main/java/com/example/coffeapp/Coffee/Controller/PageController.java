package com.example.coffeapp.Coffee.Controller;

import com.example.coffeapp.Coffee.Model.Pages;
import com.example.coffeapp.Coffee.Service.PagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page")
public class PageController {
    @Value("${upload.path}")
    private String uploadPath;
    private final PagesService pagesService;

    @GetMapping("/admin-pages")
    public String findAll(Model model) {
        Pages pages = pagesService.getById(1L).orElseGet(() -> pagesService.save(new Pages(1L)));
        model.addAttribute("pages",pages);
        return "Admin/admin-pages";
    }


    @PostMapping("/pages-save")
    public String saveCoffee(@RequestParam("mainImage1") MultipartFile mainImage1, @RequestParam("mainImage2") MultipartFile mainImage2, @RequestParam("mainImage3") MultipartFile mainImage3,
                             @RequestParam("titleP1") String titleP1, @RequestParam("textP1") String textP1, @RequestParam("imageP1") MultipartFile imageP1,
                             @RequestParam("titleP2") String titleP2, @RequestParam("textP2") String textP2, @RequestParam("imageP2") MultipartFile imageP2,
                             @RequestParam("titleP3") String titleP3, @RequestParam("textP3") String textP3, @RequestParam("imageP3") MultipartFile imageP3,
                             @RequestParam("titleP4") String titleP4, @RequestParam("textP4") String textP4, @RequestParam("imageP4") MultipartFile imageP4
                             ) throws IOException {

        Pages page = new Pages();
        Pages oldPage = pagesService.findById(1L);

        if (!(Files.exists(Path.of(uploadPath + "/img/pages/"))))
            Files.createDirectories(Path.of(uploadPath + "/img/pages/"));

        //            сохранение фото меню
//      mainImage1
        if (mainImage1.getSize() > 0) {
            Files.deleteIfExists(Paths.get(uploadPath + oldPage.getMainImage1()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + mainImage1.getOriginalFilename();
            try {
                mainImage1.transferTo(new File(uploadPath + "/img/pages/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            page.setMainImage1("/img/pages/" + FileNameUuid);

        } else {
            if (oldPage.getMainImage1() != null)
                page.setMainImage1(oldPage.getMainImage1());
        }
//      mainImage2
        if (mainImage2.getSize() > 0) {
            Files.deleteIfExists(Paths.get(uploadPath + oldPage.getMainImage2()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + mainImage2.getOriginalFilename();
            try {
                mainImage2.transferTo(new File(uploadPath + "/img/pages/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            page.setMainImage2("/img/pages/" + FileNameUuid);

        } else {
            if (oldPage.getMainImage2() != null)
                page.setMainImage2(oldPage.getMainImage2());
        }
//      mainImage3
        if (mainImage3.getSize() > 0) {
            Files.deleteIfExists(Paths.get(uploadPath + oldPage.getMainImage3()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + mainImage3.getOriginalFilename();
            try {
                mainImage3.transferTo(new File(uploadPath + "/img/pages/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            page.setMainImage3("/img/pages/" + FileNameUuid);

        } else {
            if (oldPage.getMainImage3() != null)
                page.setMainImage3(oldPage.getMainImage3());
        }

//            сохранение фото инструкции
//      imageP1

        if (imageP1.getSize() > 0) {
            Files.deleteIfExists(Paths.get(uploadPath + oldPage.getImageP1()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + imageP1.getOriginalFilename();
            try {
                imageP1.transferTo(new File(uploadPath + "/img/pages/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            page.setImageP1("/img/pages/" + FileNameUuid);

        } else {
            if (oldPage.getImageP1() != null)
                page.setImageP1(oldPage.getImageP1());
        }

//      imageP2

        if (imageP2.getSize() > 0) {
            Files.deleteIfExists(Paths.get(uploadPath + oldPage.getImageP2()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + imageP2.getOriginalFilename();
            try {
                imageP2.transferTo(new File(uploadPath + "/img/pages/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            page.setImageP2("/img/pages/" + FileNameUuid);

        } else {
            if (oldPage.getImageP2() != null)
                page.setImageP2(oldPage.getImageP2());
        }

//       imageP3

        if (imageP3.getSize() > 0) {
            Files.deleteIfExists(Paths.get(uploadPath + oldPage.getImageP3()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + imageP3.getOriginalFilename();
            try {
                imageP3.transferTo(new File(uploadPath + "/img/pages/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            page.setImageP3("/img/pages/" + FileNameUuid);

        } else {
            if (oldPage.getImageP3() != null)
                page.setImageP3(oldPage.getImageP3());
        }

//      imageP4

        if (imageP4.getSize() > 0) {
            Files.deleteIfExists(Paths.get(uploadPath + oldPage.getImageP4()));

            String uuid = UUID.randomUUID().toString();
            String FileNameUuid = uuid + "-" + imageP4.getOriginalFilename();
            try {
                imageP4.transferTo(new File(uploadPath + "/img/pages/" + FileNameUuid));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            page.setImageP4("/img/pages/" + FileNameUuid);

        } else {
            if (oldPage.getImageP4() != null)
                page.setImageP4(oldPage.getImageP4());
        }

        page.setId(1L);
        page.setTitleP1(titleP1);
        page.setTitleP2(titleP2);
        page.setTitleP3(titleP3);
        page.setTitleP4(titleP4);
        page.setTextP1(textP1);
        page.setTextP2(textP2);
        page.setTextP3(textP3);
        page.setTextP4(textP4);

        pagesService.save(page);

        return "redirect:/page/admin-pages";
    }
}
