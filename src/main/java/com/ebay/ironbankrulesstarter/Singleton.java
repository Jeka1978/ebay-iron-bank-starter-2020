package com.ebay.ironbankrulesstarter;/**
 * @author Evgeny Borisov
 */

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface Singleton {
}
