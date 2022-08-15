package java4.auction_management.controller;

import com.cloudinary.utils.ObjectUtils;
import java4.auction_management.config.CloudinaryConfig;
import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.Account;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.IAccountService;
import java4.auction_management.service.IBidService;
import java4.auction_management.service.ICategoryService;
import java4.auction_management.service.IProductService;
import java4.auction_management.service.IUserService;
import java4.auction_management.service.impl.AuctionService;
import java4.auction_management.service.impl.CategoryService;
import java4.auction_management.service.impl.ProductService;
import java4.auction_management.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    IAccountService accountService;

    @Autowired
    IUserService userService;

    @Autowired
    UserService userService2;

    @Autowired
    ProductService productService;

    @Autowired
    ICategoryService iCategoryService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    IBidService iBidService;
//    @GetMapping
//    public String showAllUser(Model model) {
//        model.addAttribute("users", userService.getAllUser());
//        return "admin/list-user";
//    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") User user, Model model) {
//        Optional<User> user = userService.getById(id);
        model.addAttribute("users", user);
        return "admin/edit-user";
    }


    @PostMapping("/edit")
    public String editUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @RequestParam("file") MultipartFile[] files) throws IOException {

        if (bindingResult.hasErrors()) {
            return "admin/edit-user";
        }



        try {
            System.out.println(user.getImage());
            System.out.println(files + "aaaaa");
            for (MultipartFile file : files
            ) {
                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                System.out.println(uploadResult.get("url").toString());
            }

            System.out.println(files);
            userService.save(user);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/edit/{id}";
        }
        redirectAttributes.addFlashAttribute("message", "Edit successful");
        return "redirect:/admin";
//        }
    }

    @GetMapping("/categories")
    public String showAllCategories(Model model) {
        model.addAttribute("categories", iCategoryService.getAll());
        return "admin/list-categories";
    }

    @GetMapping("/detail/{id}")
    public String showDetailUser(@PathVariable("id") User user, Model model) {
//                Optional<User> users = userService.getById(user.getId());
        model.addAttribute("users", user);
        return "user/edit-profile";
    }


    @GetMapping("/detailAuction/{productId}")
    public String detailAuction(@PathVariable("productId") Product product, Model model){

        model.addAttribute("products", product);
        System.out.println(product);

        String[] listImage = product.getListImage().split(" ");
        for (String image: listImage
        ) {
            System.out.println(image);
        }
        model.addAttribute("listImage", listImage);


        return "user/detailAuction";

    }
    @GetMapping("/wallet/{username}")
    public String showWalletUser(@PathVariable("username")Account account, Model model) {
        User user = account.getUser();
        model.addAttribute("users", user);

        return "user/wallet";
    }



}
