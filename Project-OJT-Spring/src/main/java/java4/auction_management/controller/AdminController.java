package java4.auction_management.controller;

import com.cloudinary.utils.ObjectUtils;
import java4.auction_management.config.CloudinaryConfig;
import java4.auction_management.entity.category.Category;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.IAccountService;
import java4.auction_management.service.ICategoryService;
import java4.auction_management.service.IUserService;
import java4.auction_management.service.impl.CategoryService;
import java4.auction_management.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
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

    @Autowired
    ICategoryService iCategoryService;

    @Autowired
    CategoryService categoryService;
    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "admin/user-management";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") User user, Model model) {
//        Optional<User> user = userService.getById(id);
        model.addAttribute("users", user);
        return "admin/edit-user";
    }


    @PostMapping("/edit")
    public String editUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @RequestParam("file") MultipartFile file) throws IOException {
//        if (bindingResult.hasErrors()) {
//            return "admin/edit-user";
//        }
        Optional<User> currentUser = userService.getById(user.getId());
        System.out.println(currentUser);

        if (file.isEmpty()) {
            userService.save(user);
            return "redirect:/admin";
        } else {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(),
                        ObjectUtils.asMap("resourcetype", "auto"));
                user.setImage(uploadResult.get("url").toString());
                if (bindingResult.hasErrors()) {
                    return "redirect:/edit/{id}";
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/edit/{id}";
            }
            userService.save(user);
            redirectAttributes.addFlashAttribute("message", "Edit successful");
            return "redirect:/admin";
        }
    }

    @GetMapping("/categories")
    public String showAllCategories(Model model) {
        model.addAttribute("categories", iCategoryService.getAll());
        return "admin/list-categories";
    }

    @GetMapping("/detail/{id}")
    public String showDetailUser(@PathVariable("id") User user, Model model) {
//                Optional<User> users = userService.getById(user.getId());
        model.addAttribute("users", user);
        return "admin/detail-user";
    }

    @GetMapping("/editCategory/{id}")
    public String showEditCategoryForm(@PathVariable("id") Category category, Model model) {

        model.addAttribute("categories", category);
        return "admin/edit-category";
    }


    @PostMapping("/editCategory")
    public String editCategory(@Valid @ModelAttribute Category category,BindingResult bindingResult, RedirectAttributes redirectAttributes)
                               {
        categoryService.save(category);

        return "redirect:categories";

    }
    @GetMapping("/createCategory")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/createCategory";
    }

    @PostMapping("/createCategory")
    public String createCategory(@Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/list-categories";
        }

        categoryService.save(category);
        return "redirect:categories";
    }
}






