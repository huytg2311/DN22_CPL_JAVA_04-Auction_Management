package java4.auction_management.service.impl;

import java4.auction_management.entity.payment.EWallet;
import java4.auction_management.repository.IEWalletRepository;
import java4.auction_management.service.IEWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EWalletService implements IEWalletService {

    @Autowired
    private IEWalletRepository ieWalletRepository;

    @Override
    public List<EWallet> getAll() {
        return ieWalletRepository.findAll();
    }

    @Override
    public Optional<EWallet> getById(Long id) {
        return ieWalletRepository.findById(id);
    }

    @Override
    public EWallet save(EWallet entity) {
        return ieWalletRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public EWallet getEWalletByAccount_Username(String username) {
        return ieWalletRepository.getEWalletByAccount_Username(username);
    }

    public void updateDeposit(EWallet eWallet, Double balance) {
        eWallet.setBalance(balance);
        ieWalletRepository.save(eWallet);
    }
}
