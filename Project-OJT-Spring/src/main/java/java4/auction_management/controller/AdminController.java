package java4.auction_management.controller;

import java4.auction_management.entity.user.User;
import java4.auction_management.service.IAccountService;
import java4.auction_management.service.IUserService;
import java4.auction_management.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
    public String editUser(@ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Optional<User> currentUser = userService.getById(user.getId());

        if (bindingResult.hasErrors()) {
            return "admin/edit-user";
        }
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Edit successful");
        return "redirect:/admin";
    }



}
