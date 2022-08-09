package java4.auction_management.service;

import java4.auction_management.dto.user.ChangePasswordRequest;
import java4.auction_management.entity.user.Account;
import java4.auction_management.entity.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


public interface IAccountService extends IService<Account, String>{

    boolean changePassword(ChangePasswordRequest changePasswordRequest);

    Boolean existByUserName(String username);

    Boolean sendOtpToEmail(String toEmail, String otp);

    Account getUserByUsername(String username);

    Account findByUsername(String username);


}
