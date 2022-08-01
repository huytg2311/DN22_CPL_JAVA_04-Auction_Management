package java4.auction_management.controller;

import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.Account;
import java4.auction_management.entity.user.User;
import java4.auction_management.repository.IUserRepository;
import java4.auction_management.service.IAccountService;
import java4.auction_management.service.IUserService;
import java4.auction_management.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import java.util.Optional;


@Controller
public class HomeController {
    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IUserService userService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ProductService productService;


    @GetMapping(value = {"/","/welcome"})
    public String welcomePage(Model model,@PageableDefault(size = 6) Pageable pageable, User user) {
//        model.addAttribute("nameAccount", accountService.getAll());
        Page<Product> products = productService.findAllProduct(pageable);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        System.out.println(currentPrincipalName);
//        User user1 = userService.getUserByUsername(currentPrincipalName);
        model.addAttribute("products", products);
        model.addAttribute("account", new Account());
//        model.addAttribute("user", user1);
//        System.out.println(user1.toString());
        return "index";
    }

    @GetMapping(value = {"/index"})
    public String welcomePage2(Model model) {
//        model.addAttribute("nameAccount", accountService.getAll());
//        User userImage = iUserRepository.findImageUser(user.getAccount().getUsername());
//        System.out.println(userImage);
//        model.addAttribute("account", new Account());
//        model.addAttribute("users", userService.getById(id));
//        model.addAttribute("user", new User());
//        System.out.println(id);
        return "index";
    }
// moi ne
    @GetMapping("/edit-profile/{username}")
    public String editProfile(@PathVariable("username") String username, Model model) {
        User user = userService.getUserByUsername(username);
        System.out.println(username);
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/edit-profile")
    public String editProfile(@ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Edit Successfully");
        return "redirect:/user-form";
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
