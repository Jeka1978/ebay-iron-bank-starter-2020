package com.ebay.ironbankrulesstarter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Evgeny Borisov
 */
@Configuration
@Import(LegacyBeansRegistrar.class)
public class LegacyBeansConf {
}
