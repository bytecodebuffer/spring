package top.validator;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.annotation.CheckUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author bz
 * @date 2020/12/25
 */
@Slf4j
@Aspect
@Component
public class CheckUserImpl implements ConstraintValidator<CheckUser,Long> {

    private CheckUser constraintAnnotation;
    // @Autowired
    //private ValidateDao validDao;


    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null || value == 0) {
            return constraintAnnotation.allowNull();
        }
        boolean doValid = this.constraintAnnotation.doValid();
        if (!doValid) {
            return true;
        }
        //Integer result = validDao.validUserId(value);
       // if(result >0){
       //     return true;
       // }
        return false;
    }

    @Override
    public void initialize(CheckUser constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }
}
