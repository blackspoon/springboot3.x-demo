package com.clx.springboot30.application.controller;

import com.clx.springboot30.application.dto.ThymeleafDto;
import com.clx.springboot30.application.form.ThymeleafForm1;
import com.clx.springboot30.application.form.ThymeleafForm2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {

    @RequestMapping("thymeleaf1")
    public String thymeleaf1(Model model, @ModelAttribute("thymeleafForm1") ThymeleafForm1 thymeleafForm1) {
        thymeleafForm1.test1 = "test1";

        ThymeleafDto thymeleafDto = new ThymeleafDto();
        thymeleafDto.colunm1 = "111";
        thymeleafDto.colunm2 = "222";
        thymeleafDto.colunm3 = "333";
        thymeleafForm1.thymeleafList.add(thymeleafDto);

        thymeleafDto = new ThymeleafDto();
        thymeleafDto.colunm1 = "aaa";
        thymeleafDto.colunm2 = "bbb";
        thymeleafDto.colunm3 = "ccc";
        thymeleafForm1.thymeleafList.add(thymeleafDto);

        model.addAttribute("thymeleafForm1", thymeleafForm1);
        return "thymeleaf1";
    }

    @RequestMapping("thymeleaf2")
    public String thymeleaf2(Model model, @ModelAttribute("thymeleafForm2") ThymeleafForm2 thymeleafForm2) {
        thymeleafForm2.test2 = "test2";
        model.addAttribute("thymeleafForm2", thymeleafForm2);
        return "thymeleaf2";
    }
}
