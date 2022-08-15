package java4.auction_management.repository;

import java4.auction_management.entity.payment.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "select t from Transaction as t where t.eWallet.account.username = ?1")
    List<Transaction> findTransactionsByUsername(String username);
}
