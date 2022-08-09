package java4.auction_management.repository;

import java4.auction_management.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long> {
}
