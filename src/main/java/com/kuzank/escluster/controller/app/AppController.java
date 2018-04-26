package com.kuzank.escluster.controller.app;

import com.kuzank.escluster.common.bean.AppAuth;
import com.kuzank.escluster.common.bean.AuthEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AppAuth(role = AuthEnum.Observation)
@RequestMapping("/app")
public class AppController {

    @RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST})
    public String about() {
        return "about";
    }

}
