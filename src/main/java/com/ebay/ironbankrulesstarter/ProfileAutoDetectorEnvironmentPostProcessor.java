package com.ebay.ironbankrulesstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Evgeny Borisov
 */
public class ProfileAutoDetectorEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if (environment.getActiveProfiles().length == 0) {
            if (getTemperature() < -50) {
                environment.setActiveProfiles("WINTER_IS_HERE");
            }else {
                environment.addActiveProfile("WINTER_IS_COMING");
            }
        }
    }

    private int getTemperature() {
        return -70;
    }
}
