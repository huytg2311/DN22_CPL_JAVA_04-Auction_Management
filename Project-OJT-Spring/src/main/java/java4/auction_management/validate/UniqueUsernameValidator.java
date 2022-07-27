package java4.auction_management.validate;

import java4.auction_management.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    AccountService accountServie;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return accountServie.getUserByUsername(username) == null;
    }
}
