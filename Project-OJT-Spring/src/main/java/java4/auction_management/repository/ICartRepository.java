package java4.auction_management.repository;

import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.cart.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "select c from Cart as c where c.user.id = ?1")
    Cart getCartByUserId(Long id);


}
