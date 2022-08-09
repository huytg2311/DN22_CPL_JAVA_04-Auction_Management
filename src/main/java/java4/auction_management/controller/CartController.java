package java4.auction_management.controller;

import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.ICartService;
import java4.auction_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {

    @Autowired
    ICartService iCartService;

    @Autowired
    IUserService iUserService;

    @GetMapping("/cart")
    public String showCart(Model model, HttpServletRequest httpServletRequest) {
        User user = iUserService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        Cart cart =  iCartService.getByUserID(user.getId());
        model.addAttribute("cart", cart);
        return "/cart/cart";
    }
}
