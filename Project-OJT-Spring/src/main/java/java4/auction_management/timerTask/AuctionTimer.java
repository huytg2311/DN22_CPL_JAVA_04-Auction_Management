package java4.auction_management.timerTask;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.bill.Bill;
import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.entity.payment.EType;
import java4.auction_management.entity.payment.EWallet;
import java4.auction_management.entity.payment.Transaction;
import java4.auction_management.service.IEWalletService;
import java4.auction_management.service.ITransactionService;
import java4.auction_management.service.impl.AuctionService;
import java4.auction_management.service.impl.BillService;
import java4.auction_management.service.impl.CartDetailService;
import java4.auction_management.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class AuctionTimer {

    @Autowired
    AuctionService auctionService;

    @Autowired
    CartService cartService;

    @Autowired
    CartDetailService cartDetailService;

    @Autowired
    BillService billService;

    @Autowired
    ITransactionService iTransactionService;

    @Autowired
    IEWalletService ieWalletService;

    Timer timer = new Timer();
    Map<Long, TimerTask> taskList = new HashMap<>();

    private TimerTask createFinishAuctionTask(Long auctionId) {
        return new TimerTask() {
            @Override
            public void run() {
                Auction auction = auctionService.getAuctionByAuctionID(auctionId);
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime finishTime = auction.getFinishTime();
                //if the auction has bid then move the product of auction tAo winner user cart
                if (!auction.getBidList().isEmpty() && !now.isBefore(finishTime)) {
                    CartDetail cartDetail = new CartDetail();
                    Bill bill = new Bill();
                    Transaction transaction = new Transaction();

                    cartDetail.setProduct(auction.getProduct());

                    //sort bidList of the auction by bid price
                    List<Bid> bidList = auction.getBidList();
                    bidList.sort(new Comparator<Bid>() {
                        @Override
                        public int compare(Bid o1, Bid o2) {
                            return Double.compare(o2.getBidPrice(), o1.getBidPrice());
                        }
                    });
                    Bid winBid = bidList.get(0);

                    cartDetail.setBid(winBid);
                    cartDetail.setCart(cartService.findCartByUser(winBid.getUser()));
                    cartDetailService.save(cartDetail);

                    bill.setCartDetail(cartDetail);
                    billService.save(bill);

                    transaction.setAmount(winBid.getBidPrice());
                    transaction.setEType(EType.BUYING);
                    transaction.setEWallet(winBid.getUser().getAccount().getEWallet());
                    iTransactionService.save(transaction);

                    EWallet eWalletOfWinner = winBid.getUser().getAccount().getEWallet();
                    eWalletOfWinner.setBalance(eWalletOfWinner.getBalance() - winBid.getBidPrice());
                    ieWalletService.save(eWalletOfWinner);

                    auction.getProduct().setSold(true);
                    auctionService.save(auction);
                }
            }
        };
    }

    public void scheduleTimerTask(Long auctionId, Long delayTimeByMillis) {
        TimerTask task = createFinishAuctionTask(auctionId);

        if (taskList.get(auctionId) != null) {
            taskList.get(auctionId).cancel();
            taskList.remove(auctionId);
        }
        timer.schedule(task, delayTimeByMillis);
        taskList.put(auctionId,task);
    }
}
