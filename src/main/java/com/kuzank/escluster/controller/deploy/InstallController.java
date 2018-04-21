package com.kuzank.escluster.controller.deploy;

import com.kuzank.escluster.common.bean.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/execute", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public ResultBean<String> execute(@RequestParam String _host, @RequestParam String _username, @RequestParam String _password,
                                      @RequestParam String _nodeName, @RequestParam String _nodeMaster,
                                      @RequestParam String _noteData, @RequestParam String _memory,
                                      @RequestParam String _tcpPort, @RequestParam String _httpPort) {

        return new ResultBean<>("success");
    }

}
