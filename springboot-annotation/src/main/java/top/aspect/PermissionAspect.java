package top.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author bz
 * @date 2020/11/11
 */
@Aspect
@Component
public class PermissionAspect {

    private final Logger logger = LoggerFactory.getLogger(PermissionAspect.class);

    @Pointcut("@annotation(top.annotation.Permission)")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable{
        logger.info("进入aop");
        Object[] objects = joinPoint.getArgs();
        Signature signature =  joinPoint.getSignature();
        String metnodName = signature.getName();
        logger.info("methodName:"+metnodName);
        if(!checkPermission(objects[0].toString(),objects[1].toString())){
            throw  new RuntimeException("没有权限");
        }

    }

    public boolean checkPermission(String username,String password){
        if("root".equals(username)&&"123456".equals(password)){
            return true;
        }
        return false;
    }

}
