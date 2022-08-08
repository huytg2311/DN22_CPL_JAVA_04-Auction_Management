package java4.auction_management.repository;

import java4.auction_management.entity.auction.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAuctionRepository extends JpaRepository<Auction, Long> {

    Auction findAuctionByProductProductId(Long productId);




//    @Query("select * from auction as a where a.product_id = ?1 order by a.", nativeQuery = true)

}

