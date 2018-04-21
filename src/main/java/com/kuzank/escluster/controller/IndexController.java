package com.kuzank.escluster.controller;

import com.kuzank.escluster.common.bean.AppAuth;
import com.kuzank.escluster.common.bean.AuthEnum;
import com.kuzank.escluster.common.bean.ResultBean;
import com.kuzank.escluster.common.exception.CheckException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kuzan
 * @since 2018/01/21
 */
@Controller
public class IndexController {


    @RequestMapping(value = {"/", "/index", "/home", "/root"})
    @AppAuth(role = AuthEnum.Observation)
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }


    @RequestMapping(value = "/you")
    @ResponseBody
    public ResultBean<String> you() {

        return new ResultBean<String>(getString());
    }


    private String getString() {

        if (1 == 1) {
            throw new CheckException("checkException");
        }

        return "";
    }
}
