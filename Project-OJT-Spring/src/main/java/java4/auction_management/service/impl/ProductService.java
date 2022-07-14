package java4.auction_management.service.impl;

import java4.auction_management.entity.product.Product;
import java4.auction_management.repository.IProductRepository;
import java4.auction_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public List<Product> getAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Product save(Product entity) {
        return iProductRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }
}
