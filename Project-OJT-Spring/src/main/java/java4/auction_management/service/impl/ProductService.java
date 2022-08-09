package java4.auction_management.service.impl;

import java4.auction_management.entity.auction.Auction;
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
        return Optional.of(iProductRepository.getById(id));
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

    @Override
    public List<Product> findProductStatus() {
        return null;
    }


    @Override
    public void saveAllProductList(List<Product> productList) {
        for (Product product : productList) {
            iProductRepository.save(product);
        }
    }


    @Override
    public List<Auction> findWaitingAuctions() {
        return iProductRepository.getWaitingAuctions();
    }

    @Override
    public Product getProductByAuctionId(Long id) {
        return iProductRepository.getProductByAuctionId(id);
    }


    @Override
    public Optional<Product> findById(Long productId){
        return iProductRepository.findById(productId);
    }
}
