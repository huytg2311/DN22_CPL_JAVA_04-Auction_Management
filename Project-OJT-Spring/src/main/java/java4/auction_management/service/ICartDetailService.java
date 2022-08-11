package java4.auction_management.service;

import java4.auction_management.entity.cart.CartDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICartDetailService extends IService<CartDetail, Long>{
    CartDetail getCartDetailByCartID(Long id);

    List<CartDetail> getAllCartByUserId(Long id);

}
