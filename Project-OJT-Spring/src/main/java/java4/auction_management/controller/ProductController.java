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
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
        return "/products/service";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                @RequestParam("file")MultipartFile file) throws IOException {

        if (file.isEmpty() && bindingResult.hasErrors()) {

            return "redirect:/create";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            product.setProductImage(uploadResult.get("url").toString());
            productService.save(product);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/create";
        }
        redirectAttributes.addFlashAttribute("message", "Edit successful");
        return "redirect:/index2";
    }
}
