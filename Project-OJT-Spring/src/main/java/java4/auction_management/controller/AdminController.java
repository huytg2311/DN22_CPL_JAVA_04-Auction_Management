package java4.auction_management.controller;

import com.cloudinary.utils.ObjectUtils;
import java4.auction_management.config.CloudinaryConfig;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.IAccountService;
import java4.auction_management.service.IUserService;
import java4.auction_management.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    IAccountService accountService;

    @Autowired
    IUserService userService;

    @Autowired
    UserService userService2;

    @GetMapping
    public String showAllUser(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "admin/list-user";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") User user, Model model) {
//        Optional<User> user = userService.getById(id);
        model.addAttribute("users", user );
        return "admin/edit-user";
    }


    @PostMapping("/edit")
    public String editUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @RequestParam("file") MultipartFile file) throws IOException {
        Optional<User> currentUser = userService.getById(user.getId());
        System.out.println(currentUser);

        if (file.isEmpty()) {
            return "redirect:/edit/{id}";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            user.setImage(uploadResult.get("url").toString());
            userService.save(user);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/edit/{id}";
        }
            redirectAttributes.addFlashAttribute("message", "Edit successful");
            return "redirect:/admin";
        }
    }






