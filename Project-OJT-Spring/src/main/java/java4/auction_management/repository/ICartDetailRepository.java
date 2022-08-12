package java4.auction_management.repository;

import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.cart.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query(value = "select * from cart_detail as c where c.cart_id = ?1", nativeQuery = true)
    CartDetail getCartDetailByCartID(Long id);

    @Query(value = "select * from cart_detail as d join cart as c on c.cart_id = d.cart_id where c.user_id = ?1", nativeQuery = true)
    List<CartDetail> getAllCartByUserId(Long id);

    CartDetail getCartDetailByCartDetailID(Long id);
}
