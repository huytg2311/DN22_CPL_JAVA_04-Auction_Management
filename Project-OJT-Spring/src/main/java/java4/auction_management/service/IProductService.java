package java4.auction_management.service;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IProductService extends IService<Product, Long>{

    Page<Product> findAllProduct(Pageable pageable);

    List<Product> findProductStatus();

    void saveAllProductList(List<Product> productList);


    Optional<Product> findById(Long productId);

    List<Auction> findWaitingAuctions();

    Product getProductByAuctionId(Long id);

}
