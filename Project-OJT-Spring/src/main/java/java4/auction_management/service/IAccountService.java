package java4.auction_management.service;

import java4.auction_management.dto.user.ChangePasswordRequest;
import java4.auction_management.entity.user.Account;

import java.util.Optional;


public interface IAccountService extends IService<Account, String>{

    boolean changePassword(ChangePasswordRequest changePasswordRequest);

    Boolean existByUserName(String username);

    Boolean sendOtpToEmail(String toEmail, String otp);

    Account getUserByUsername(String username);

    Account findByUsername(String username);

    Boolean existsAccountByPassword(String password);

    Optional<Account> getAccountEnable(String username);




}
