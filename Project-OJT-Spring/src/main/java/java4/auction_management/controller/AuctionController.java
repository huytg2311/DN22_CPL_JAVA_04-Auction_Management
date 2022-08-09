package java4.auction_management.controller;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.service.impl.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/auctions")
public class AuctionController {
    @Autowired
    AuctionService auctionService;

    @GetMapping("/my-auctions")
    public String loadAuction(Model model, HttpServletRequest httpServletRequest){
        List<Auction> auctions = auctionService.findAuctionsByUsername(httpServletRequest.getUserPrincipal().getName());
        model.addAttribute("auctionList", auctions);
        return "user/auction";
    }
}
