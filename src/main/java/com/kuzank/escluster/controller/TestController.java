package com.kuzank.escluster.controller;

import com.kuzank.escluster.common.bean.ResultBean;
import com.kuzank.escluster.common.exception.CheckException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kuzan
 * @since 2018/01/28
 */
@Controller
public class TestController {


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
