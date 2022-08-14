package java4.auction_management.controller;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.impl.*;
import java4.auction_management.timerTask.AuctionTimer;
import java4.auction_management.validate.BidValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping(value = "/bid")
public class BidController {

    @Autowired
    BidService bidService;

    @Autowired
    UserService userService;

    @Autowired
    AuctionService iAuctionService;

    @Autowired
    CartService cartService;

    @Autowired
    BidValidator bidValidator;

    @Autowired
    AuctionTimer auctionTimer;

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
        System.out.println(bid.getUser().getAccount().getUsername());
        System.out.println(user);
        bid.setUser(user);

        String responseMessage = bidValidator.validateBid(bid, auction);

        if (responseMessage.contains("true")){
            if(ChronoUnit.SECONDS.between(bid.getBidTime(),auction.getFinishTime()) < 30) {
                auction.setFinishTime(LocalDateTime.now().plusSeconds(30));
                iAuctionService.save(auction);

                auctionTimer.scheduleTimerTask(auction.getAuctionID(), ChronoUnit.MILLIS.between(LocalDateTime.now(),auction.getFinishTime()));
            }
            bid.setAuction(auction);
            bidService.save(bid);


        }

        return responseMessage;
    }

    @GetMapping("/my-bidding")
    public String loadAuction(Model model, HttpServletRequest httpServletRequest) {
        User user = userService.getUserByUsername(httpServletRequest.getUserPrincipal().getName());
        List<Bid> bid = bidService.findBiddingByUserId(user.getId());
        model.addAttribute("bids", bid);
        return "user/bidding";
    }
}
