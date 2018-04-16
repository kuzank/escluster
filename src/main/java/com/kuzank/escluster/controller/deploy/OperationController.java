package com.kuzank.escluster.controller.deploy;

import com.kuzank.escluster.common.bean.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-16
 */
@Controller
@RequestMapping("/operation")
public class OperationController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "operation";
    }

    public ResultBean<String> install() {
        return new ResultBean<>("");
    }

}
