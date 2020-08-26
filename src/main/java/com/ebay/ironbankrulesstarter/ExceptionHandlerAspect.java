package com.ebay.ironbankrulesstarter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Evgeny Borisov
 */
//@Component
//@Aspect
public class ExceptionHandlerAspect {

    @Autowired
    private RavenProps ravenProps;


    @Around("@annotation(Deprecated)")
    public Object handleDeprecatedMethods(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(System.nanoTime());
        Object retVal = pjp.proceed();
        System.out.println(System.nanoTime());
        return retVal;
    }


    @AfterThrowing(value = "execution(* com.ebay.ironbank.services.*.*(..))", throwing = "ex")
    public void doAround(NotEnoughMoneyException ex) {
        System.out.println("Sending raven  " + ravenProps.getDestination() + " " + ex.getMessage());
    }


}



