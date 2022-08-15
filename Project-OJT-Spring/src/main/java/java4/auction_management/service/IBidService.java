package java4.auction_management.service;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.product.Product;
import org.springframework.security.access.method.P;

import java.util.List;
import java.util.Optional;

public interface IBidService extends IService<Bid,Long> {
    public List<Bid> getAll();
    public Optional<Bid> getById(Long id);
    public void deleteById(Integer id);

//    List<Bid> getBidsByProductId(Product productId);

    List<Bid> listBidSort(Long auctionID);

    List<Bid> findAllByOrderByBidPriceDesc();

    List<Bid> findBiddingByUserId(Long userId);



}
