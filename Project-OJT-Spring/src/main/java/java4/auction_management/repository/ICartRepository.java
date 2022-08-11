package java4.auction_management.repository;

import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUser (User user);
}
