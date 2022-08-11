package java4.auction_management.controller;

import com.cloudinary.utils.ObjectUtils;
import java4.auction_management.config.CloudinaryConfig;
import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.category.Category;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.IBidService;
import java4.auction_management.service.ICategoryService;
import java4.auction_management.service.impl.ProductService;
import java4.auction_management.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
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

    @Autowired
    private IBidService iBidService;

    @Autowired
    private UserService userService;


    @ModelAttribute("categories")
    public List<Category> categoryList() {
        return iCategoryService.getAll();
    }

    @GetMapping("/create")
    public String createFormProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/products/create-product";
    }
    @GetMapping("/auction")
    public String showAllProductPosted(Model model) {
        model.addAttribute("products", productService.getAll());
        return "/user/auction";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                @RequestParam("file") MultipartFile[] files, HttpServletRequest httpServletRequest) throws IOException {

        try {
            LocalDateTime datePost = LocalDateTime.now();
            User user = userService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
            product.getAuction().setUser(user);
            product.setDatePost(datePost);
            StringBuilder listImage = new StringBuilder();
            for (MultipartFile file : files) {

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
        return "redirect:/";
    }






    @GetMapping(value = "/productDetail")
    public String productDetail(){ return "product-detail";}

    @GetMapping("/edit/{id}")
    public String showEditProduct(@PathVariable("id") Product product, Model model) {
        String username = product.getAuction().getUser().getAccount().getUsername();
        model.addAttribute("products", product );
        String[] listImages = product.getListImage().split(" ");
        model.addAttribute("listImages", listImages);
        return "/products/edit-product";
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                @RequestParam("file") MultipartFile[] files) throws IOException {
        String username = product.getAuction().getUser().getAccount().getUsername();

        for (MultipartFile m: files
             ) {
            if (m.isEmpty()) {
                productService.save(product);
                return "redirect:/products/auction/" + username;
            }
        }
        try {
            StringBuilder listImage = new StringBuilder();
            for (MultipartFile file : files) {

                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                System.out.println("2" + (uploadResult.get("url").toString()));
                listImage.append(uploadResult.get("url").toString()).append(" ");
            }
            product.setListImage(listImage.toString());
            productService.save(product);

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/index";
        }

        redirectAttributes.addFlashAttribute("message", "Edit successful");
        return "redirect:/products/auction/" + username;
    }

}
