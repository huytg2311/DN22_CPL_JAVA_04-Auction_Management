package java4.auction_management.repository;

import java4.auction_management.entity.user.Account;
import java4.auction_management.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Query(value = "select * from user as u inner join account as a on u.account_username = a.username\n" +
            "where a.role = 'ROLE_USER'",nativeQuery = true)
    List<User> getAllUser();

    @Query(value = "select u from User u where u.email = ?1")
    User findByEmail(String email);

    User findByResetPasswordToken(String token);



}
