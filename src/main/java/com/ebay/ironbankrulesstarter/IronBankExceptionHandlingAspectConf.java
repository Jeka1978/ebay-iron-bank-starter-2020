package com.ebay.ironbankrulesstarter;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Evgeny Borisov
 */

@Configuration

@EnableConfigurationProperties({RavenProps.class})
public class IronBankExceptionHandlingAspectConf {

//    @Bean
//    @ConditionOnProd
//    @ConditionalOnProperty(value = "raven.destination")
//    public ExceptionHandlerAspect ironBankExceptionHandlerAspect() {
//        return new ExceptionHandlerAspect();
//    }

    @Bean
    public CustomPointcut customPointcut() {
        return new CustomPointcut();
    }

    @Bean
    public RavenSenderMethodInterceptor ravenSenderMethodInterceptor() {
        return new RavenSenderMethodInterceptor();
    }


    @Bean
    @ConditionOnProd
//    @ConditionalOnProperty("raven.target-package")
    @ConditionalOnProperty("raven.destination")
    public DefaultPointcutAdvisor advisor(CustomPointcut customPointcut, RavenSenderMethodInterceptor ravenSenderMethodInterceptor) {

        return new DefaultPointcutAdvisor(customPointcut, ravenSenderMethodInterceptor);

    }
}




