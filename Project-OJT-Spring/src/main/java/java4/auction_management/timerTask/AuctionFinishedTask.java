//package java4.auction_management.timerTask;
//
//import java4.auction_management.entity.auction.Auction;
//import java4.auction_management.entity.cart.CartDetail;
//import java4.auction_management.service.impl.AuctionService;
//import java4.auction_management.service.impl.CartDetailService;
//import java4.auction_management.service.impl.CartService;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.TimerTask;
//
//@Getter
//@Setter
//@Component
//public class AuctionFinishedTask extends TimerTask {
//
//    private Long auctionId;
//
//    @Autowired
//    AuctionService auctionService;
//
//    @Autowired
//    CartService cartService;
//
//    @Autowired
//    CartDetailService cartDetailService;
//
//
//    @Override
//    public void run() {
//        Auction auction = auctionService.getAuctionByAuctionID(auctionId);
//        System.out.println(auction.getBidList().size());
//        //if the auction has bid then move the product of auction to winner user cart
//        if (!auction.getBidList().isEmpty()){
//            CartDetail cartDetail = new CartDetail();
//            cartDetail.setProduct(auction.getProduct());
//            cartDetail.setCart(cartService.findCartByUser(auction.getUser()));
//            cartDetailService.save(cartDetail);
//            auction.getProduct().setSold(true);
//            auctionService.save(auction);
//        }
//    }
//
//
//}
