package java4.auction_management.controller;
import com.cloudinary.utils.ObjectUtils;
import java4.auction_management.config.CloudinaryConfig;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.Account;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.IAccountService;
import java4.auction_management.service.IUserService;
import java4.auction_management.validate.AccountValidator;
import java4.auction_management.validate.LoginValidator;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IUserService userService;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    AccountValidator accountValidator;

    @Autowired
    LoginValidator loginValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, Account account) {
        model.addAttribute("account", account);
        return "login";
    }

    @GetMapping("/signUp")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/signUp")
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                          @RequestParam("file") MultipartFile file) throws IOException {
        accountValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "create-user";
        }
        if (!file.isEmpty()) {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                user.setImage(uploadResult.get("url").toString());

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/edit/{id}";
            }
        }

        userService.save(user);
//        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Add successful");
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success(Model model) {
        return "success";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "/index";
    }

}
