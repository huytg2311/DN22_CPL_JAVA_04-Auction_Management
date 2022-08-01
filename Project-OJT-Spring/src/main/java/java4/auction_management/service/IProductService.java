package java4.auction_management.service;

import java4.auction_management.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService extends IService<Product, Long>{

    Page<Product> findAllProduct(Pageable pageable);

    List<Product> findProductStatus();

    void saveAllProductList(List<Product> productList);
}
