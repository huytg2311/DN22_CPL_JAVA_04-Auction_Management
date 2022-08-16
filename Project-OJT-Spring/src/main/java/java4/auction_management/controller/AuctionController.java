package java4.auction_management.controller;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.product.Product;
import java4.auction_management.service.impl.AuctionService;
import java4.auction_management.service.impl.BidService;
import java4.auction_management.service.impl.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/auctions")
public class AuctionController {
    @Autowired
    AuctionService auctionService;

    @Autowired
    BidService bidService;

    @Autowired
    CartDetailService cartDetailService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    HttpServletRequest httpServletRequest;


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
        if (bidList.size() > 5) bidList = bidList.subList(0, 5);
        auction.setBidList(bidList);

        model.addAttribute("auction", auction);
        return "products/post";
    }

    @GetMapping("/get-winner-cartDetail/{auctionId}")
    @ResponseBody
    public String getWinBidByAuctionId(@PathVariable("auctionId") Long auctionId) {
        Auction auction = auctionService.getById(auctionId).orElseThrow(() -> {
            throw new IllegalStateException("No auction was found by Id: " + auctionId);
        });
        System.out.println(auction.getProduct().getCartDetail().toString());
        return auction.getProduct().getCartDetail().toString();
    }

    @GetMapping("/auction-result/{auctionId}")
    @ResponseBody
    public String getAuctionResult(@PathVariable Long auctionId) {
        String result = "{\"auctionResult\": \"visitor\"}";

        if (httpServletRequest.getUserPrincipal() != null) {
            Auction auction = auctionService.getById(auctionId).orElseThrow(() -> {
                throw new IllegalStateException("No auction was found by Id: " + auctionId);
            });

            if (auction.getProduct().getCartDetail() == null) return result;

            String winnerUsername = auction.getProduct().getCartDetail().getBid().getUser().getAccount().getUsername();

            Iterator<Bid> bidList = auction.getBidList().iterator();

            while (bidList.hasNext()) {
                Bid bid = bidList.next();
                String usernameOfBid = bid.getUser().getAccount().getUsername();
                String usernameCurrent = httpServletRequest.getUserPrincipal().getName();
                if (usernameCurrent.equals(usernameOfBid)) {
                    if (usernameCurrent.equals(winnerUsername)) {
                        result = result.replace("visitor", "winner");
                    } else {
                        result = result.replace("visitor", "loser");
                    }
                    break;
                }
            }
        }

        return result;

    }

    @MessageMapping("/auctions/new-bid-alert/{auctionId}")
    public void alertClientToReloadBidList(@DestinationVariable String auctionId, @RequestBody String alert) {
        simpMessagingTemplate.convertAndSend("/auctions/new-bid-alert-receiver/" + auctionId, alert);
    }

    @GetMapping("/create")
    public String createFormProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/products/create-product";
    }


    @GetMapping("/search")
    public String searchAuction(HttpServletRequest httpServletRequest, Model model) {
        String name = httpServletRequest.getParameter("name");
        String nameProduct = httpServletRequest.getParameter("nameProduct");
        double maxPrice, minPrice;

        if(httpServletRequest.getParameter("minPrice").equals("")){
            minPrice = 0.0;
        } else {
            minPrice = Double.parseDouble(httpServletRequest.getParameter("minPrice"));
        }
        if(httpServletRequest.getParameter("maxPrice").equals("")){
            maxPrice = Double.MAX_VALUE;
        } else {
            maxPrice = Double.parseDouble(httpServletRequest.getParameter("maxPrice"));
        }

        List<Auction> auctionList = auctionService.searchAuction(name, nameProduct, minPrice, maxPrice);
        model.addAttribute("auctions", auctionList);
        return "index";

    }
}
