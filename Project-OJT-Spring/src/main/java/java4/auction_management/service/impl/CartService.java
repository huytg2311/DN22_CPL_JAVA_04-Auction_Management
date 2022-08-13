package java4.auction_management.service.impl;

import java4.auction_management.entity.cart.Cart;
import java4.auction_management.entity.user.User;
import java4.auction_management.repository.ICartRepository;
import java4.auction_management.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    ICartRepository iCartRepository;

    @Override
    public List<Cart> getAll() {
        return null;
    }

    @Override
    public Optional<Cart> getById(Long id) {
        return iCartRepository.findById(id);
    }

    @Override
    public Cart save(Cart entity) {
        return iCartRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Cart getByUserID(Long id) {
        return iCartRepository.getById(id);
    }

    public Cart findCartByUser (User user){
        return iCartRepository.findCartByUser(user);
    }

    @Override
    public List<Cart> getCartByUserId(Long id) {
        return iCartRepository.getCartByUserId(id);
    }
}
