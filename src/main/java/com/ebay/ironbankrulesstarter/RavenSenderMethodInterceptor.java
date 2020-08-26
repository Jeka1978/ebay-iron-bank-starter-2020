package com.ebay.ironbankrulesstarter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author Evgeny Borisov
 */
public class RavenSenderMethodInterceptor implements MethodInterceptor {
    @Autowired
    private RavenProps ravenProps;

    private Map<NotEnoughMoneyException, Void> map = new WeakHashMap<>();

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("method was intercepted "+methodInvocation.getMethod().getName());
        try {
            return methodInvocation.proceed();
        } catch (NotEnoughMoneyException ex) {
            if (!map.containsKey(ex)) {

                System.out.println("Sending raven  " + ravenProps.getDestination() + " " + ex.getMessage());
                map.put(ex, null);
            }
            throw ex;

        }
    }
}
