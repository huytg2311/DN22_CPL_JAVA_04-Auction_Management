package java4.auction_management.repository;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface  IProductRepository extends JpaRepository<Product, Long> {



    @Override
    Optional<Product> findById(Long productId);

    @Query(value = "select p from Auction  as p where  p.auctionStatus = 'WAITING'")
    List<Auction> getWaitingAuctions();



    @Query(value = "select p from Product as p where p.auction.auctionID = ?1")
    Product getProductByAuctionId(Long id);
}
