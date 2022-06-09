package java4.auction_management.controller;


import com.fasterxml.jackson.core.JsonParseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.io.BaseEncoding;
import java4.auction_management.entity.user.Account;
import java4.auction_management.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

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

    @GetMapping(value = {"/index2"})
    public String welcomePage2(Model model) {
//        model.addAttribute("nameAccount", accountService.getAll());
        model.addAttribute("account", new Account());
        return "index2";
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
