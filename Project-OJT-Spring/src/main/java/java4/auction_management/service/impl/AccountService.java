package java4.auction_management.service.impl;

import java4.auction_management.dto.user.ChangePasswordRequest;
import java4.auction_management.entity.user.Account;
import java4.auction_management.entity.user.User;
import java4.auction_management.repository.IAccountRepository;
import java4.auction_management.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

//    @Autowired
//    private JavaMailSender javaMailSender;

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

    public void updatePassword(Account account, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);

        account.setPassword(encodedPassword);
        accountRepository.save(account);
    }

    @Override
    public Boolean existByUserName(String username) {
        return this.accountRepository.existsByUsername(username);
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

    @Override
    public Boolean sendOtpToEmail(String email, String otp) {
//        try {
//            MimeMessage message = this.javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//
//            helper.setTo(email);
//            helper.setSubject("MÃ OTP - ĐỔI MẬT KHẨU");
//            helper.setText("<h3>Xin chao ! </h3>" +
//                    "<p>Vui long khong chia se ma nay cho bat ky ai.</p>" +
//                    "<p>Ma OTP cua ban la: <span style='color: blue; font-size: x-large'>" + otp + "</span></p>" +
//                    "<p>Link dan den trang chu: <a style='color: red; text-decoration: underline' href='http://localhost:4200'>bam vao day</a></p>", true
//            );
//            this.javaMailSender.send(message);
//            System.out.println("Send OTP to mail success !!!");
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
        return false;
    }

    @Override
    public Account getUserByUsername(String username) {
        return accountRepository.getUserByUsername(username);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public void processOAuthPostLogin(String username) {
        Account existUser = accountRepository.getUserByUsername(username);

        if (existUser == null) {
            Account newAccount = new Account();
            newAccount.setUsername(username);
            newAccount.setEnable(true);


            accountRepository.save(newAccount);
        }

    }

}
