package java4.auction_management.repository;

import java4.auction_management.entity.auction.Auction;
import java4.auction_management.entity.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, String> {

    Boolean existsByUsername(String username);

    @Query("select a.enable from Account a where a.username = :username")
    Boolean getEnableByUsername(@Param("username") String username);

    @Query("SELECT a FROM Account a WHERE a.username = :username")
    public Account getUserByUsername(@Param("username") String username);

    @Query("SELECT a FROM Account a WHERE a.password = :password")
    public Account getPasswordByUsername(@Param("password") String password);

    @Modifying
    @Query(value = "update account set password = :password where username = :username", nativeQuery = true)
    Account savePassword(String username, String password);

    Account findByUsername(String username);

    Boolean existsAccountByPassword(String password);

    @Query(value = "select a from Account as a where a.username = ?1 and a.enable = true")
    Optional<Account> getAccountEnable(String username);

}
