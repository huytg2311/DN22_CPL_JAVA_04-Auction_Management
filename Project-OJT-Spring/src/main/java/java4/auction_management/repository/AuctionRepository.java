package java4.auction_management.repository;

import java4.auction_management.entity.auction.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuctionRepository extends JpaRepository <Auction, Integer> {
    @Query(value = "SELECT * FROM Auction AS a WHERE a.finish_time < SYSDATETIME() AND a.is_finish = false", nativeQuery = true)
    public Optional<Auction> findExpiredAuction();
}
