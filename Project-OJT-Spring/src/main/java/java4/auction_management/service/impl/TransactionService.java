package java4.auction_management.service.impl;

import java4.auction_management.entity.payment.Transaction;
import java4.auction_management.repository.ITransactionRepository;
import java4.auction_management.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    ITransactionRepository iTransactionRepository;

    @Override
    public List<Transaction> getAll() {
        return null;
    }

    @Override
    public Optional<Transaction> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Transaction save(Transaction entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Transaction> findTransactionsByUsername(String username) {
        return iTransactionRepository.findTransactionsByUsername(username);
    }
}
