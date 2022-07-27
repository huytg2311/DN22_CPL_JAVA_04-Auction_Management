package java4.auction_management.controller;

import com.cloudinary.utils.ObjectUtils;
import java4.auction_management.config.CloudinaryConfig;
import java4.auction_management.entity.category.Category;
import java4.auction_management.entity.product.Product;
import java4.auction_management.service.ICategoryService;
import java4.auction_management.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ICategoryService iCategoryService;

    @ModelAttribute("categories")
    public List<Category> categoryList() {
        return iCategoryService.getAll();
    }

    @GetMapping("/create")
    public String createFormProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/products/create-product";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                @RequestParam("file")MultipartFile[] files) throws IOException {

//        if (file.isEmpty()) {
//
//            return "redirect:/create";
//        }
        try {
            StringBuilder listImage = new StringBuilder();
            for (MultipartFile file: files) {

                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                System.out.println("2" + (uploadResult.get("url").toString()));
                listImage.append(uploadResult.get("url").toString()).append(" ");
            }
            product.setListImage(listImage.toString());
            productService.save(product);

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/create";
        }

        redirectAttributes.addFlashAttribute("message", "Edit successful");
        return "redirect:/index2";
    }

    @GetMapping("/load/{id}")
    public String load(@PathVariable("id") Product product, Model model){

        model.addAttribute("products", product);

        String[] listImage = product.getListImage().split(" ");
        for (String image: listImage
             ) {
            System.out.println(image);
        }

        model.addAttribute("listImages", listImage);
        return "/products/post";

    }
}
