package java4.auction_management.repository;

import java4.auction_management.entity.auction.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuctionRepository extends JpaRepository<Auction, Long> {

    Auction findAuctionByProductProductId(Long productId);

    @Query(value = "select a from Auction a where a.user.account.username = ?1")
    List<Auction> findAuctionsByUsername(String username);


//    @Query("select * from auction as a where a.product_id = ?1 order by a.", nativeQuery = true)

}

