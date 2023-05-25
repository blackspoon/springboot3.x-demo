package com.clx.springboot30.application.controller;

import com.clx.springboot30.exception.CustomException1;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @RequestMapping(value = "/error1")
    public String error1() throws CustomException1 {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new CustomException1("CustomException1");
        }
        return "success";
    }

    @RequestMapping(value = "/error2")
    public String error2() throws CustomException1 {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new CustomException1("module A",
                    "user.password.retry.limit.count",
                    new Object[]{3});
//           return new CustomException1("module A", "user.password.retry.limit.count", new Object[]{3}).getMessage();
        }
        return "success";
    }

    @RequestMapping(value = "/errorOther")
    public String errorOther() throws IndexOutOfBoundsException {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("array out of bound");
        }
        return "success";
    }
}
