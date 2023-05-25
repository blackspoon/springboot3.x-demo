package com.clx.springboot30.adviser;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
//@RestControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addAttributes(HttpServletRequest req, Model model) {
        model.addAttribute("level", 3);
        req.getSession().setAttribute("model", model);
    }
}
