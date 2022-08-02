package java4.auction_management.repository;

import java4.auction_management.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface  IProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product as p where p.username = ?1", nativeQuery = true)
    List<Product> getProductsByUsername(String username);

    @Override
    Optional<Product> findById(Long productId);
}
