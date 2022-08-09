package java4.auction_management.repository;


import java4.auction_management.entity.user.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Account, String> {

    Account getAccountByUsername(String username);


}
