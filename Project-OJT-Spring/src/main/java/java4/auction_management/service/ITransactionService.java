package java4.auction_management.service;

import java4.auction_management.entity.payment.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface ITransactionService extends IService<Transaction, Long>{
    @Override
    Transaction save(Transaction entity);
}
