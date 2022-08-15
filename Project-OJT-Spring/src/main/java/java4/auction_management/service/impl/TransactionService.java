package java4.auction_management.service.impl;

import java4.auction_management.entity.payment.Transaction;
import java4.auction_management.service.ITransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Override
    public List<Transaction> getAll() {
        return null;
    }

    @Override
    public Optional<Transaction> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Transaction save(Transaction entity) {
        return null;
    }
}
