package java4.auction_management.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CurrentDateTimeValidator implements ConstraintValidator<DateTimeBeforeCurrent, LocalDate> {
    @Override
    public void initialize(DateTimeBeforeCurrent constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate!=null){
            return localDate.isBefore(LocalDate.now());
        } else {return false;}
    }

}
