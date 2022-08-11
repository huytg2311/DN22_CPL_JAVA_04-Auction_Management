package java4.auction_management.validate;

import java4.auction_management.entity.user.Account;
import java4.auction_management.entity.user.User;
import java4.auction_management.service.IAccountService;
import java4.auction_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {
    @Autowired
    IAccountService accountService;

    @Autowired
    IUserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (accountService.existByUserName(user.getAccount().getUsername())) {
            errors.rejectValue("account.username", "account.username", new String[]{}, "Username is duplicated");
        }

        if (userService.existByEmail(user.getEmail())) {
            errors.rejectValue("email", "email", new String[]{}, "Email is duplicated");
        }

        if (userService.existByPhone_number(user.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", "phoneNumber", new String[]{}, "Phone number is duplicated");
        }

//        if(!user.getAccount().getRetypePassword().equals(user.getAccount().getPassword())){
//            errors.rejectValue("retypePassword", "retypePassword", new  String[]{}, "Password do not match");
//
//        }

    }
}
