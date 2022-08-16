package java4.auction_management.service;

import java4.auction_management.entity.payment.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@Primary

public interface ITransactionService extends IService<Transaction, Long>{

    List<Transaction> findTransactionsByUsername(String username);

}
