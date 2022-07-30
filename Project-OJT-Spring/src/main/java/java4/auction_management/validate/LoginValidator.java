package java4.auction_management.validate;

import java4.auction_management.entity.user.Account;
import java4.auction_management.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    @Autowired
    IAccountService iAccountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;
        if (!iAccountService.existByUserName(account.getUsername())) {
            errors.rejectValue("username", "username", new String[]{}, "User is incorrect");
        }
    }
}
