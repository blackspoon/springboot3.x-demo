package com.clx.springboot30.adviser;

import com.clx.springboot30.exception.CustomException1;
import com.clx.springboot30.exception.CustomException2;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.clx.springboot30.constant.GlobalConst.ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(CustomException1.class)
    public String accessExceptionHandler(HttpServletRequest req, Model model, Exception e) {
        logger.error(e.getMessage(), e);
        model.addAttribute("message", e.getMessage());
        return "error1";
    }

    @ExceptionHandler(CustomException2.class)
    public String loginExceptionHandler(HttpServletRequest req, Model model, Exception e) {
        logger.error(e.getMessage(), e);
        return "error2";
    }

    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(HttpServletRequest req, Model model, Exception e) {
        logger.error(e.getMessage(), e);
        Model sessionModel = (Model)req.getSession().getAttribute("model");
        if(sessionModel != null) {
            model.addAllAttributes(sessionModel.asMap());
        }
        return ERROR;
    }
}
