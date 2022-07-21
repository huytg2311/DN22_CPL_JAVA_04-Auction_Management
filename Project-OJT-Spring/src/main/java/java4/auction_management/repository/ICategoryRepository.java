package java4.auction_management.repository;

import java4.auction_management.entity.category.Category;
import java4.auction_management.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Long> {


}
