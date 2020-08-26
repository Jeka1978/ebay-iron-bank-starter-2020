package com.ebay.ironbankrulesstarter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Evgeny Borisov
 */

@ConfigurationProperties(prefix = "raven")
@Data
public class RavenProps {
    private String destination;
    private String targetPackage;
}
