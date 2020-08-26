package com.ebay.ironbankrulesstarter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Evgeny Borisov
 */
public class ProxyBPP implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Arrays.stream(bean.getClass().getMethods()).anyMatch(method -> method.isAnnotationPresent(Deprecated.class))) {


            if (bean.getClass().getInterfaces().length > 0) {
             return    Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.isAnnotationPresent(Deprecated.class)) {
                            System.out.println("DEPERE");
                            method.invoke(bean, args);
                        }
                        method.invoke(bean, args);

                        return null;
                    }
                });
            }

           return Enhancer.create(bean.getClass(), new org.springframework.cglib.proxy.InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return null;
                }
            });


        }
        return bean;
    }
}
