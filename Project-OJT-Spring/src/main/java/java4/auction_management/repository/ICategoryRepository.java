package java4.auction_management.repository;

import java4.auction_management.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {


}
