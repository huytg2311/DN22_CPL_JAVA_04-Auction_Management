package java4.auction_management.repository;

import java4.auction_management.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
