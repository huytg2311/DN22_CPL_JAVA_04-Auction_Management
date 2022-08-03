package java4.auction_management.controller;

import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.impl.ProductService;
import java4.auction_management.service.impl.BidService;
import java4.auction_management.service.impl.ProductService;
import java4.auction_management.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/bid")
public class BidController {

    @Autowired
    BidService bidService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService ProductService;

    @RequestMapping(value = "/createBid", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes={"application/json"})
    @ResponseBody
    public void createBid(@RequestBody Bid bid) {
        Product product =  ProductService.findById(bid.getProduct().getProductId()).orElseThrow(() -> {
            throw new IllegalStateException("No product found");
        });
        User user = userService.getUserByUsername(bid.getUser().getAccount().getUsername());
        System.out.println(bid.getUser().getAccount().getUsername());
        System.out.println(user);
        bid.setUser(user);
        bid.setProduct(product);
        bidService.save(bid);
    }
}
