package com.ebay.ironbankrulesstarter;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * @author Evgeny Borisov
 */
public class LegacyBeansRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {


    private Set<Class<?>> classes;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

//        String packagesToScan = ((DefaultListableBeanFactory) registry).getBean(Environment.class).getProperty("raven.legacy-packages-to-scan");



        for (Class<?> aClass : classes) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(aClass);
            beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
            beanDefinition.addQualifier(new AutowireCandidateQualifier(Legacy.class));
            registry.registerBeanDefinition(aClass.getSimpleName().toLowerCase(),beanDefinition);
        }

    }

    @Override
    public void setEnvironment(Environment environment) {
        var scanner = new Reflections(environment.getProperty("raven.legacy-packages-to-scan"));
        classes = scanner.getTypesAnnotatedWith(Singleton.class);
    }
}
