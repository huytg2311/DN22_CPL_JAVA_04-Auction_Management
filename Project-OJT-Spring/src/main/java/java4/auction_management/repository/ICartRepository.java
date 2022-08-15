package java4.auction_management.repository;

import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUser (User user);

    @Query(value = "select c from Cart as c where c.user.id = ?1")
    List<Cart> getCartByUserId(Long id);



}
