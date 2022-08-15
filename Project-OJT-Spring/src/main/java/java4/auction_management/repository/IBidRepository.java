package java4.auction_management.repository;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface IBidRepository extends JpaRepository<Bid, Integer> {

//    public List<Bid> getBidsByProduct(Product product);
    public Optional<Bid> getBidByBidId(Long id);

    @Query(value = "select * from bid as b where b.auction_id = ?1 order by b.bid_price desc", nativeQuery = true)
    List<Bid> listBidSort(Long auctionID);

    List<Bid> findAllByOrderByBidPriceDesc();

    @Query(value = "select b from Bid as b where b.user.id = ?1 and current_timestamp <= b.auction.finishTime  group by b.auction order by b.bidPrice")
    List<Bid> findBiddingByUserId(Long userId);

    @Query(value = "select b from Bid as b where b.user.account.username = ?1 group by b.auction order by b.bidPrice")
    List<Bid> getBidsHadBeenBidByUsername(String username);



//    List<Bid> findAllByProduct
}
