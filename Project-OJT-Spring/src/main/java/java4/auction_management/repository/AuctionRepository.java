package java4.auction_management.repository;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuctionRepository extends JpaRepository <Auction, Integer> {

}
