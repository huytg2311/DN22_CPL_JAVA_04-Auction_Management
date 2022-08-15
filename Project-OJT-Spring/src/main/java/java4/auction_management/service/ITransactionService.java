package java4.auction_management.service;

import java4.auction_management.entity.payment.Transaction;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface ITransactionService extends IService<Transaction, Long>{
    @Override
    Transaction save(Transaction entity);

    List<Transaction> findTransactionsByUsername(String username);

}
