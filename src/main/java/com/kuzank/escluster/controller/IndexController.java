package com.kuzank.escluster.controller;

import com.kuzank.escluster.common.bean.AppAuth;
import com.kuzank.escluster.common.bean.AuthEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kuzan
 * @since 2018/01/21
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index", "/home", "/root"})
    @AppAuth(role = AuthEnum.Observation)
    public String index() {
        return "/index";
    }

}
