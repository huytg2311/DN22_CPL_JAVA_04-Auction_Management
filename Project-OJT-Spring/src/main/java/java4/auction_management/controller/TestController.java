package java4.auction_management.controller;


import java4.auction_management.entity.product.Product;
import java4.auction_management.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/test")
    public String getAllProductStatus(Model model) {
        model.addAttribute("products", productService.findProductStatus());
        return "/team";
    }

    @GetMapping("/test/{id}")
    public String getProductDetail(@PathVariable("id")Product product, Model model) {
        model.addAttribute("products", product);
        return "/testimonial";
    }

    @PostMapping("/test")
    public String accessProduct(@ModelAttribute Product product, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Edit successful");
        return "redirect:/index2";
    }
}
