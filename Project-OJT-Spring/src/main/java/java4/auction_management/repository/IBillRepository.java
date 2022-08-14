package java4.auction_management.repository;

import java4.auction_management.entity.bill.Bill;

import java4.auction_management.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBillRepository  extends JpaRepository<Bill, Long> {
    @Query(value = "select b from Bill as b where b.cartDetail.cartDetailID = ?1")
    Bill getBillCartDetailId(Long cartDetailId);


}
