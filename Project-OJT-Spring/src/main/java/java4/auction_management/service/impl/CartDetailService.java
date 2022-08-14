package java4.auction_management.service.impl;

import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.entity.product.Product;
import java4.auction_management.repository.ICartDetailRepository;
import java4.auction_management.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService {

    @Autowired
    ICartDetailRepository iCartDetailRepository;

    @Override
    public Optional<CartDetail> findCartDetailByProduct(Product product) {
        return Optional.empty();
    }

    @Override
    public CartDetail getCartDetailByCartID(Long id) {
        return iCartDetailRepository.getCartDetailByCartID(id);
    }

    @Override
    public List<CartDetail> getAllCartByUserId(Long id) {
        return iCartDetailRepository.getAllCartByUserId(id);
    }

    @Override
    public CartDetail getCartDetailByCartDetailID(Long id) {
        return iCartDetailRepository.getCartDetailByCartDetailID(id);
    }

    @Override
    public List<CartDetail> getAll() {
        return null;
    }

    @Override
    public Optional<CartDetail> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public CartDetail save(CartDetail entity) {
        return iCartDetailRepository.save(entity);
    }


    @Override
    public void deleteById(Long id) {

    }
}
