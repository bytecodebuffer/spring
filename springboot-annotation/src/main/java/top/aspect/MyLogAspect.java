package top.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义注解实现类
 *
 * @author bz
 * @date 2020/11/11
 */
@Aspect
@Component
public class MyLogAspect {

    private final Logger logger = LoggerFactory.getLogger(MyLogAspect.class);

    @Pointcut("@annotation(top.annotation.MyLog)")
    public void pointCut(){}

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("around 前方法");
        joinPoint.proceed();
         logger.info("around 后方法");
    }

    @Before("pointCut()")
    public void doBefore(){
         logger.info("doBefore 方法");
    }

    @AfterReturning("pointCut()")
    public void doAfterReturn(JoinPoint joinPoint){
         logger.info("doAfterReturn 方法");
    }

    @After("pointCut()")
    public void doAfter(){
         logger.info("doAfter 方法");
    }

    @AfterThrowing("pointCut()")
    public void doThrowable(){
         logger.info("doThrowable 方法");
    }

}
