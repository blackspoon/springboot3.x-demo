package com.clx.springboot30.exception;

public class CustomException2 extends Exception{
    /**
     * @param message
     */
    public CustomException2(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public CustomException2(String message, Throwable cause) {
        super(message, cause);
    }
}
