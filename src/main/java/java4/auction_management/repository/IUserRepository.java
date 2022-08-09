package java4.auction_management.repository;

import java4.auction_management.entity.user.Account;
import java4.auction_management.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query(value = "select * from user as u inner join account as a on u.account_username = a.username\n" +
            "where a.role = 'ROLE_USER'",nativeQuery = true)
    List<User> getAllUser();

    @Query(value = "select * from user as u where u.email = ?1", nativeQuery = true)
    User findByEmail(String email);

    User findByResetPasswordToken(String token);

    @Query(value = "select * from user as u where u.account_username = ?1", nativeQuery = true)
    User findUserByUserName(String username);

    @Query(value = "select u from User u where u.phone_number like :?1", nativeQuery = true)
    User findByPhone(String phone);

    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByEmail(String email);

//    @Query(value = "select u from User u where u.account.username = ?1")
//    User findImageUser(String account_username);

//    Page<User> findByUserName(Optional<String> id,Pageable pageable);





}
