package java4.auction_management.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.IAuctionService;
import java4.auction_management.service.impl.*;
import java4.auction_management.service.impl.ProductService;
import java4.auction_management.validate.BidValidator;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/bid")
public class BidController {

    @Autowired
    BidService bidService;

    @Autowired
    UserService userService;


    @Autowired
    IAuctionService iAuctionService;

    @Autowired
    CartService cartService;

    @Autowired
    BidValidator bidValidator;

    @GetMapping("/cart/{username}")
    public String showCart(@PathVariable("username") String username, Model model, HttpServletRequest httpServletRequest) {
        User user = userService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        Cart cart =  cartService.getByUserID(user.getId());
        model.addAttribute("cart", cart);
        return "/cart/cart";
    }

    @RequestMapping(value = "/createBid", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes={"application/json"})
    @ResponseBody
    public String createBid(@RequestBody Bid bid) {
        Auction auction =  iAuctionService.getById(bid.getAuction().getAuctionID()).orElseThrow(() -> {
            throw new IllegalStateException("No auction id found");
        });
        User user = userService.getUserByUsername(bid.getUser().getAccount().getUsername());
        bid.setUser(user);

        String responseMessage = bidValidator.validateBid(bid, auction);

        if (responseMessage.contains("true")){

            bid.setAuction(auction);

            bidService.save(bid);
        }

        return responseMessage;
    }
}
