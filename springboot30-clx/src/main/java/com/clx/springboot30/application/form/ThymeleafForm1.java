package com.clx.springboot30.application.form;

import com.clx.springboot30.application.dto.ThymeleafDto;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ThymeleafForm1 implements Serializable {

    public String test1;

    public List<ThymeleafDto> thymeleafList = new ArrayList<>();
}
