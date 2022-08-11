package java4.auction_management.controller;

import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.ICartDetailService;
import java4.auction_management.service.ICartService;
import java4.auction_management.service.impl.CartDetailService;
import java4.auction_management.service.impl.CartService;
import java4.auction_management.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService iCartService;

    @Autowired
    UserService userService;

    @Autowired
    CartDetailService cartDetailService;

    @GetMapping("/cart")
    public String showCartUser(HttpServletRequest httpServletRequest, Model model) {
        User user = userService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        List<Cart> cart = iCartService.getCartByUserId(user.getId());

        model.addAttribute("carts", cart);
        return "/cart/cart";
    }
}
