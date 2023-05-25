package com.clx.springboot30.exception;

public class CustomException1 extends BaseException{

    /**
     * @param message
     */
    public CustomException1(String message) {
        super(message);
    }

    public CustomException1(String module, String code, Object[] args) {
        super(module ,code, args, null);
    }

}
