package java4.auction_management.validate;

import java4.auction_management.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String phone_number, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByPhone(phone_number) == null;
    }
}
