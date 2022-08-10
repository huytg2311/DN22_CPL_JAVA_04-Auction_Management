package java4.auction_management.service;

import java4.auction_management.entity.cart.Cart;
import org.springframework.stereotype.Service;

@Service
public interface ICartService extends IService<Cart, Long>{

    Cart getByUserID(Long id);

    Cart getCartByUserId(Long id);

}
