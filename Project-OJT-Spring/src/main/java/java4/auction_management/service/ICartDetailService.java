package java4.auction_management.service;

import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.entity.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICartDetailService extends IService<CartDetail, Long>{

    Optional<CartDetail> findCartDetailByProduct(Product product);

    CartDetail getCartDetailByCartID(Long id);

    List<CartDetail> getAllCartByUserId(Long id);

    CartDetail getCartDetailByCartDetailID(Long id);


}
