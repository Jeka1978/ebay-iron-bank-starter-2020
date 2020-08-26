package com.ebay.ironbankrulesstarter;

/**
 * @author Evgeny Borisov
 */
public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
