package com.kuzank.escluster.controller.deploy;

import com.kuzank.escluster.common.bean.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>Description: 部署安装 ElasticSearch 应用的 Controller</p>
 *
 * @author kuzan
 * @since 2018-04-16
 */
@Controller
@RequestMapping("/install")
public class InstallController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "install";
    }

    public ResultBean<String> install() {
        return new ResultBean<>("");
    }

}
