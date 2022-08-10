package java4.auction_management.service;

import java4.auction_management.entity.cart.CartDetail;
import org.springframework.stereotype.Service;

@Service
public interface ICartDetailService extends IService<CartDetail, Long>{
    CartDetail getCartDetailByCartID(Long id);
}
