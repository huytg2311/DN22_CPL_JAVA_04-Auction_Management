package java4.auction_management.controller;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.product.Product;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.IAuctionService;
import java4.auction_management.service.impl.*;
import java4.auction_management.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    AuctionService auctionService;

    @Autowired
    CartService cartService;


    @RequestMapping(value = "/createBid", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes={"application/json"})
    @ResponseBody
    public void createBid(@RequestBody Bid bid) {
        Auction auction =  auctionService.getById(bid.getAuction().getAuctionID()).orElseThrow(() -> {
            throw new IllegalStateException("No auction id found");
        });
        User user = userService.getUserByUsername(bid.getUser().getAccount().getUsername());
        System.out.println(bid.getUser().getAccount().getUsername());
        System.out.println(user);
        bid.setUser(user);
        bid.setAuction(auction);
        bidService.save(bid);
    }
}
