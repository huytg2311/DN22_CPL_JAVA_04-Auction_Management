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
    private AuctionService auctionService;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    AccountValidator accountValidator;


    @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model,@PageableDefault(size = 8) Pageable pageable) {
        Page<Auction> auctions = auctionService.getAllAuctionByStatus(pageable);
        model.addAttribute("auctions", auctions);
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
    @GetMapping(value = "/about")
    public String about(){ return "about";}
    @GetMapping(value = "/guide")
    public String guide(){ return "guide";}


}
