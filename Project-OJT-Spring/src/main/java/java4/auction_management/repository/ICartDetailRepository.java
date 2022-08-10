package java4.auction_management.repository;

import java4.auction_management.entity.cart.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query(value = "select c from CartDetail as c where c.cart.cardId = ?1")
    CartDetail getCartDetailByCartID(Long id);

}
