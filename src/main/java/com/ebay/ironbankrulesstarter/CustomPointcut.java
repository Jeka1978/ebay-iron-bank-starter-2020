package com.ebay.ironbankrulesstarter;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

/**
 * @author Evgeny Borisov
 */
public class CustomPointcut extends DynamicMethodMatcherPointcut {
    @Autowired
    private RavenProps ravenProps;

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... objects) {


//        boolean contains = targetClass.getPackage().getName().contains(ravenProps.getTargetPackage());
//        System.out.println("contains = " + contains);
//        return method.getName().toLowerCase().contains("work");
        return true;
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> cls) {
//                System.out.println(cls.getSimpleName());
                return cls.getPackage().getName().contains(ravenProps.getTargetPackage());
            }
        };
    }
}
