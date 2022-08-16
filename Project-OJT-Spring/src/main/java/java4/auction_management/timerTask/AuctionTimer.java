package java4.auction_management.timerTask;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.bill.Bill;
import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.entity.payment.EType;
import java4.auction_management.entity.payment.EWallet;
import java4.auction_management.entity.payment.Transaction;
import java4.auction_management.service.IEWalletService;
import java4.auction_management.service.IProductService;
import java4.auction_management.service.ITransactionService;
import java4.auction_management.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class AuctionTimer {

    @Autowired
    IProductService iProductService;
    @Autowired
    AuctionService auctionService;

    @Autowired
    CartService cartService;

    @Autowired
    CartDetailService cartDetailService;

    @Autowired
    BillService billService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    EWalletService eWalletService;

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
                    Transaction buyerTransaction = new Transaction();
                    Transaction sellerTransaction = new Transaction();

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

                    //transaction and ewallet buyer
                    buyerTransaction.setAmount(winBid.getBidPrice());
                    buyerTransaction.setEType(EType.BUYING);
                    buyerTransaction.setEWallet(winBid.getUser().getAccount().getEWallet());
                    buyerTransaction.setDateTransaction(LocalDateTime.now());
                    transactionService.save(buyerTransaction);

                    EWallet eWalletOfBuyer = winBid.getUser().getAccount().getEWallet();
                    eWalletOfBuyer.setBalance(eWalletOfBuyer.getBalance() - winBid.getBidPrice());
                    eWalletService.save(eWalletOfBuyer);

                    //transaction and ewallet seller
                    sellerTransaction.setAmount(winBid.getBidPrice());
                    sellerTransaction.setEType(EType.SELLING);
                    sellerTransaction.setEWallet(winBid.getUser().getAccount().getEWallet());
                    sellerTransaction.setDateTransaction(LocalDateTime.now());
                    transactionService.save(sellerTransaction);

                    EWallet eWalletOfSeller = auction.getUser().getAccount().getEWallet();
                    eWalletOfSeller.setBalance(eWalletOfSeller.getBalance() + winBid.getBidPrice());
                    eWalletService.save(eWalletOfSeller);

                    //add bill
                    bill.setCartDetail(cartDetail);
                    billService.save(bill);

                    //
                    auction.getProduct().setSold(true);
                    iProductService.save(auction.getProduct());
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
