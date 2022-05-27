package java4.auction_management.controller;

import java4.auction_management.entity.user.User;
import java4.auction_management.service.IAccountService;
import java4.auction_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IUserService userService;

    @GetMapping
    public String showAllUser(Model model) {
        model.addAttribute("listUsers", userService.getAllUser());
        return "admin/list-user";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        return "admin/edit-user";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return  "admin/edit-user";
        }

        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Edit Successfully");
        return "redirect:/admin";
    }

//    @GetMapping("/add")
//    public String showAddForm(Model model) {
//        model.addAttribute("user", new User());
//        return "create-user";
//    }
//
//    @PostMapping("/add")
//    public String addUser(@ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        userService.save(user);
//        redirectAttributes.addFlashAttribute("message", "Add successful");
//        return "redirect:/admin";
//    }
}
