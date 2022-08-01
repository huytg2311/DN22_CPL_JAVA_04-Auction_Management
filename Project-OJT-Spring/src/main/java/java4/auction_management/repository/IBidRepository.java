package java4.auction_management.repository;

import java4.auction_management.entity.bid.Bid;
import java4.auction_management.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBidRepository extends JpaRepository<Bid, Integer> {

    public List<Bid> getBidsByProduct(Product product);
    public Optional<Bid> getBidByBidId(Long id);
}
