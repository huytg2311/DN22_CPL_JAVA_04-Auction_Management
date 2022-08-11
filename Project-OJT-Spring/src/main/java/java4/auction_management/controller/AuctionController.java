package java4.auction_management.controller;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.service.impl.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/auctions")
public class AuctionController {
    @Autowired
    AuctionService auctionService;

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
        Auction auction =
                auctionService.getById(auctionId).orElseThrow(() -> {
                    throw new IllegalStateException("No auction found!");
                });
        List<Bid> bidList = auction.getBidList();
        bidList.sort(new Comparator<Bid>() {
            @Override
            public int compare(Bid o1, Bid o2) {
                return Double.compare(o1.getBidPrice(), o2.getBidPrice());
            }
        });

        auction.setBidList(bidList);
        model.addAttribute("auction", auction);

        String[] listImages =
                auction.getProduct().getListImage().split(" ");
        model.addAttribute(
                "listImages",
                listImages);
        return "products/post";

    }
}
