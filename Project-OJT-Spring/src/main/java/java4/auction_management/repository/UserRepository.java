package java4.auction_management.repository;


import java4.auction_management.entity.user.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<Account, String> {
    @Query("SELECT a FROM Account a WHERE a.username = : username")
    Account getUserByUsername(@Param("username") String username);
}
