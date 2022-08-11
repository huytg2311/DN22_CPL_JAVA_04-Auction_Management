package java4.auction_management.service.impl;

import java4.auction_management.entity.cart.CartDetail;
import java4.auction_management.repository.ICartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService{
    @Autowired
    ICartDetailRepository iCartDetailRepository;


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
