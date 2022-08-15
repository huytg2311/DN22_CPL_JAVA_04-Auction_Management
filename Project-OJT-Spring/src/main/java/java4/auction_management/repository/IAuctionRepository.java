package java4.auction_management.repository;

import java4.auction_management.entity.auction.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAuctionRepository extends JpaRepository<Auction, Long> {

    Auction findAuctionByProductProductId(Long productId);

    Auction findAuctionByUserId(Long id);

    Auction getAuctionByAuctionID(Long id);

//    @Query(value = "select a from Auction a where a.user.id = ?1")
//    List<Product> getAuctionByUserId(Long id);

    @Query(value = "select a from Auction  as a where  a.user.id = ?1")
    List<Auction> getAuctionsByUserId(Long id);

    @Query(value = "select a from Auction a where a.user.account.username = ?1")
    List<Auction> findAuctionsByUsername(String username);

    @Query("select a from Auction as a where a.auctionStatus = 'ACCEPTED' and current_timestamp < a.finishTime")
    Page<Auction> getAllAuctionByStatus(Pageable pageable);

//    Auction getAuctionByAuctionID(Long id);


//    @Query("select * from auction as a where a.product_id = ?1 order by a.", nativeQuery = true)

}

