package java4.auction_management.validate;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.user.ERole;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class BidValidator {


    public String validateBid(Bid bid, Auction currentAuction) {
        String responseMess = "{\"isSuccess\": true, \"errorMess\":\"none\"}";

        List<Bid> bidList = currentAuction.getBidList();
        bidList.sort(new Comparator<Bid>() {
            @Override
            public int compare(Bid o1, Bid o2) {
                return Double.compare(o2.getBidPrice(), o1.getBidPrice());
            }
        });


        if (bid.getBidTime().isAfter(currentAuction.getFinishTime())) {
            responseMess = responseMess.replace("true", "false");
            responseMess = responseMess.replace("none", "Your bid is invalid because the Auction has ended!");
        } else if (bid.getUser().getAccount().getUsername().equals(currentAuction.getUser().getAccount().getUsername())) {
            responseMess = responseMess.replace("true", "false");
            responseMess = responseMess.replace("none", "Cannot place bid in your own auctions!");
        } else if (bid.getUser().getAccount().getRole() == ERole.ROLE_ADMIN) {
            responseMess = responseMess.replace("true", "false");
            responseMess = responseMess.replace("none", "Admin cannot place bids!");
        } else if(bid.getUser().getAccount().getEWallet().getBalance() < bid.getBidPrice()){
            responseMess = responseMess.replace("true", "false");
            responseMess = responseMess.replace("none", "Your balance is not enough to place this bid!");
        }else if (bidList.isEmpty()) {
            if (bid.getBidPrice() < currentAuction.getReservePrice()) {
                responseMess = responseMess.replace("true", "false");
                responseMess = responseMess.replace("none", "Your bid is the first! It must be greater or equal the reserve price!");
            } else if (bid.getBidPrice() - currentAuction.getReservePrice() > 5 * currentAuction.getStepPrice()) {
                responseMess = responseMess.replace("true", "false");
                responseMess = responseMess.replace("none", "Your bid is the first! It can only be up to 5 price steps above the reserve price!");

            }
        } else {
            if (bid.getBidPrice() <= bidList.get(0).getBidPrice()) {
                responseMess = responseMess.replace("true", "false");
                responseMess = responseMess.replace("none", "Your bid is invalid! It must be greater than current bid at least one step price.");
            } else if (bid.getBidPrice() - bidList.get(0).getBidPrice() > 5 * currentAuction.getStepPrice()) {
                responseMess = responseMess.replace("true", "false");
                responseMess = responseMess.replace("none", "Your bid is invalid! It can only be up to 5 price steps above your current bid");
            }
        }
        return responseMess;
    }

}