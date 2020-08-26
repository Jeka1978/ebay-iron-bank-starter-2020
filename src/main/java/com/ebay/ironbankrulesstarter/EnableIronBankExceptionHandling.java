package com.ebay.ironbankrulesstarter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Evgeny Borisov
 */

@Retention(RetentionPolicy.RUNTIME)
@Import(IronBankExceptionHandlingAspectConf.class)
public @interface EnableIronBankExceptionHandling {
}
