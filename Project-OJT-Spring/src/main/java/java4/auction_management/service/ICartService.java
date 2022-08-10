package java4.auction_management.service;

import java4.auction_management.entity.cart.Cart;

public interface ICartService extends IService<Cart, Long>{

    Cart getByUserID(Long id);
}
