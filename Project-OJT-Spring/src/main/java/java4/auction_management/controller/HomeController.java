package java4.auction_management.controller;

import com.cloudinary.utils.ObjectUtils;
import java4.auction_management.config.CloudinaryConfig;
import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.Account;
import java4.auction_management.entity.user.User;
import java4.auction_management.repository.IUserRepository;
import java4.auction_management.service.IAccountService;
import java4.auction_management.service.IAuctionService;
import java4.auction_management.service.IUserService;
import java4.auction_management.service.impl.AccountService;
import java4.auction_management.service.impl.AuctionService;
import java4.auction_management.service.impl.ProductService;
import java4.auction_management.service.impl.UserService;
import java4.auction_management.validate.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import java.util.Optional;


@Controller
public class HomeController {
    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    AccountValidator accountValidator;


    @GetMapping(value = {"/","/welcome"})
    public String welcomePage(Model model,@PageableDefault(size = 8) Pageable pageable) {
        Page<Auction> auctions = auctionService.getAllAuctionByStatus(pageable);
        model.addAttribute("auctions", auctions);
        return "index";
    }


//        String[] listImage = products.getListImage().split(" ");
//        for (String image: listImage
//        ) {
//            System.out.println(image);
//        }
//        model.addAttribute("listImage", listImage);


//        String[] listImages = product.getListImage().split(" ");
//        model.addAttribute("listImages", listImages);
//        model.addAttribute("user", user1);
//        System.out.println(user1.toString());




    @GetMapping("/view-profile/{username}")
    public String editProfile(@PathVariable("username") String username, Model model) {
        User user = userService.getUserByUsername(username);
        Account account = accountService.getById(user.getAccount().getUsername()).orElseThrow(() -> {
            throw new IllegalStateException("Not account found");
        });
        System.out.println(username);
        model.addAttribute("user", user);
        model.addAttribute("account", account);
        return "user-form";
    }

    @PostMapping("/edit-profile")
    public String editUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @RequestParam("file") MultipartFile file) throws IOException {
//        Account account = accountService.getById(user.getAccount().getUsername()).orElseThrow(() -> {
//            throw new IllegalStateException("No account found");
//        });
//        account.setRetypePassword(user.getAccount().getRetypePassword());
//        user.setAccount(account);
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

    @GetMapping("/changePassword/{username}")
    public String formChangePassword(@PathVariable("username") String username, Model model) {
        Account account1 = accountService.findByUsername(username);
        User user = userService.getUserByUsername(username);
        model.addAttribute("username", username);
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

    @GetMapping(value = {"/403"})
    public String accessDenied(Model model) {
        return "403";
    }

    @GetMapping(value = {"/register"})
    public String registerForm() {
        return "register";
    }
    @GetMapping(value = "/about")
    public String about(){ return "about";}
    @GetMapping(value = "/guide")
    public String guide(){ return "guide";}


}
