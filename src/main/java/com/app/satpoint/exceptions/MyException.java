package com.app.satpoint.exceptions;

/**
 * Mainly used by the Validator to notify if a user's input is not valid
 */
public final class MyException extends RuntimeException{

    public MyException(String message){
        super(message);
    }
}
