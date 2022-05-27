package java4.auction_management.controller;


import java4.auction_management.entity.user.Account;
import java4.auction_management.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private IAccountService accountService;

    @GetMapping(value = {"/","/welcome"})
    public String welcomePage(Model model) {
//        model.addAttribute("nameAccount", accountService.getAll());
        model.addAttribute("account", new Account());
        return "index";
    }

    @GetMapping(value = {"/403"})
    public String accessDenied(Model model) {
        return "403";
    }

    @GetMapping(value = {"/register"})
    public String registerForm() {
        return "register";
    }




}
