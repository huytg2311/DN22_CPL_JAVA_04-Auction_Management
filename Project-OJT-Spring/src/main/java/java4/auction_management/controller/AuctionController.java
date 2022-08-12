package java4.auction_management.controller;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.service.impl.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/auctions")
public class AuctionController {
    @Autowired
    AuctionService auctionService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/my-auctions")
    public String loadAuction(Model model, HttpServletRequest httpServletRequest) {
        List<Auction> auctions =
                auctionService.findAuctionsByUsername(httpServletRequest.getUserPrincipal().getName());
        model.addAttribute(
                "auctionList",
                auctions);
        return "user/auction";
    }

    @GetMapping("/detail-auction/{id}")
    public String load(@PathVariable(
            "id") Long auctionId,
                       Model model) {
        Auction auction = auctionService.getById(auctionId).orElseThrow(() -> {
                    throw new IllegalStateException("No auction found!");
        });

        //sort bidList of the auction by bid price
        List<Bid> bidList = auction.getBidList();
        bidList.sort(new Comparator<Bid>() {
            @Override
            public int compare(Bid o1, Bid o2) {
                return Double.compare(o2.getBidPrice(), o1.getBidPrice());
            }
        });
        if (bidList.size() > 5) bidList = bidList.subList(0,5);
        auction.setBidList(bidList);

        model.addAttribute("auction", auction);
        return "products/post";
    }

    @GetMapping("/get-win-bid/{auctionId}")
    public Bid getWinBidByAuctionId(@RequestParam Long auctionId){
        Auction auction = auctionService.getById(auctionId).orElseThrow(() -> {
            throw  new IllegalStateException("No auction was found by Id: " + auctionId);
        });
        auction.getProduct().getCartDetail().;
    }

    @MessageMapping("/auctions/new-bid-alert/{auctionId}")
    public void alertClientToReloadBidList(@DestinationVariable String auctionId, @RequestBody String alert) {
        simpMessagingTemplate.convertAndSend("/auctions/new-bid-alert-receiver/" + auctionId, alert);
    }
}
