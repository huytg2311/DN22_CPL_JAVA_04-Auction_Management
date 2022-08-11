package java4.auction_management.repository;

import java4.auction_management.entity.cart.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    @Override
    CartDetail save(CartDetail cartDetail);
}
