package com.kuzank.escluster.controller.deploy;

import com.jcraft.jsch.Session;
import com.kuzank.escluster.common.bean.AppAuth;
import com.kuzank.escluster.common.bean.AuthEnum;
import com.kuzank.escluster.common.bean.OperateStatus;
import com.kuzank.escluster.common.bean.ResultBean;
import com.kuzank.escluster.common.helper.JsonResponse;
import com.kuzank.escluster.entity.LinuxConnEntity;
import com.kuzank.escluster.util.SSHUtil;
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
@AppAuth(role = AuthEnum.Observation)
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


    @RequestMapping(value = "/getMemory", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public JsonResponse getMemory(@RequestParam String _host, @RequestParam String _username, @RequestParam String _password) throws Exception {

        LinuxConnEntity linuxConnEntity = new LinuxConnEntity();
        linuxConnEntity.setHost(_host);
        linuxConnEntity.setPort(LinuxConnEntity.defaultPort);
        linuxConnEntity.setUsername(_username);
        linuxConnEntity.setPassword(_password);

        Session session = SSHUtil.getSession(linuxConnEntity);

        if (!session.isConnected()) {
            session.disconnect();
            JsonResponse jsonResponse = new JsonResponse(OperateStatus.LINUX_CANT_CONNECT);
            return jsonResponse;
        }

        String executeResult = SSHUtil.executeCommand(session, "free -h");
        String[] arr = executeResult.split("\\s+");
        if (arr.length > 12) {
            executeResult = arr[13];
            session.disconnect();
            System.out.println(executeResult);
            return new JsonResponse(OperateStatus.SUCCESS, executeResult);
        } else {
            session.disconnect();
            return new JsonResponse(OperateStatus.LINUX_GET_MEMORY_ERROE);
        }
    }


    @RequestMapping(value = "/connectLinux", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public JsonResponse connectLinux(@RequestParam String _host, @RequestParam String _username, @RequestParam String _password) {

        LinuxConnEntity linuxConnEntity = new LinuxConnEntity(_host, LinuxConnEntity.defaultPort, _username, _password);
        Session session = SSHUtil.getSession(linuxConnEntity);

        if (session.isConnected()) {
            session.disconnect();
            return JsonResponse.OK;
        } else {
            session.disconnect();
            return new JsonResponse(OperateStatus.LINUX_CANT_CONNECT);
        }
    }

}
