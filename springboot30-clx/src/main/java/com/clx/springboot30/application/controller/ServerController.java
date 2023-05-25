package com.clx.springboot30.application.controller;

import com.clx.springboot30.utils.ServerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServerController {

    @RequestMapping(value = "/getServer")
    public String getServerInfo(ModelMap modelMap) throws Exception {
        ServerUtil server = new ServerUtil();
        server.copyTo();
        modelMap.put("server", server);
        return "server";
    }
}
