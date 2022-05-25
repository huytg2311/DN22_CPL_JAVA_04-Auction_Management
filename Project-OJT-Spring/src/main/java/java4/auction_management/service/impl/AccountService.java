package java4.auction_management.service.impl;

import java4.auction_management.dto.user.ChangePasswordRequest;
import java4.auction_management.entity.user.Account;
import java4.auction_management.repository.IAccountRepository;
import java4.auction_management.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        Optional<Account> accountOptional = this.getById(changePasswordRequest.getUsername());
        return accountOptional.map(account -> {
            if (passwordEncoder.matches(changePasswordRequest.getOldPassword(), account.getPassword())
            && !passwordEncoder.matches(changePasswordRequest.getNewPassword(), account.getPassword())) {
                    account.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                    accountRepository.save(account);
                    return true;
            }
            return false;
        }).orElse(false);
    }

    @Override
    public Boolean existByUserName(String username) {
        return this.accountRepository.existsByUsername(username);
    }

    @Override
    public Boolean sendOtpToEmail(String toEmail, String otp) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Optional<Account> getById(String username) {
        return this.accountRepository.findById(username);
    }

    @Override
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public void deleteById(String id) {

    }
}
