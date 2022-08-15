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
import java4.auction_management.validate.AccountValidator;
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

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    AccountValidator accountValidator;




    @GetMapping("/detail/{id}")
    public String showDetailUser(@PathVariable("id") User user, Model model) {

        model.addAttribute("users", user);
        return "user/edit-profile";
    }

    @GetMapping("/view-profile")
    public String editProfile( Model model) {

        User user = userService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        Account account = accountService.getById(user.getAccount().getUsername()).orElseThrow(() -> {
            throw new IllegalStateException("Not account found");
        });

        model.addAttribute("user", user);
        model.addAttribute("account", account);
        return "user-form";
    }

    @PostMapping("/edit-profile")
    public String editUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @RequestParam("file") MultipartFile file) throws IOException {

        accountValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/user-form";
        }

        if (file.isEmpty()) {
            userService.saveUserNotPassword(user);
            return "/user-form";
        } else {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                user.setImage(uploadResult.get("url").toString());
                if (bindingResult.hasErrors()) {
                    return "redirect:/view-profile/{username}";
                }
                userService.saveUserNotPassword(user);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/view-profile/{username}";
            }
            return "/user-form";
        }
    }

    @GetMapping("/changePassword")
    public String formChangePassword(Model model) {
        Account account1 = accountService.findByUsername(httpServletRequest.getUserPrincipal().getName());
        User user = userService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        model.addAttribute("username", httpServletRequest.getUserPrincipal().getName());
        model.addAttribute("user", user);
        return "/change-password";
    }

    @PostMapping("/savePassword")
    public String changePassword(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = accountService.findByUsername(username);
        accountService.updatePassword(account, password);
        model.addAttribute("message", "Change password successfully !");
        return "/success";
    }

    @GetMapping("/changeAvatar")
    public String formChangeAvatar(HttpServletRequest httpServletRequest, Model model) {
        User user = userService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        model.addAttribute("user", user);
        return "form-change-image";
    }

    @PostMapping("/changeAvatar")
    public String changeAvatar(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file, Model model) {
        String image = httpServletRequest.getParameter("image");
        User user = userService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            image = uploadResult.get("url").toString();
            userService.changeAvatar(user, image);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/view-profile/{username}";
        }
        return "redirect:/changeAvatar";
    }



}
