package com.kuzank.escluster.controller.deploy;

import com.jcraft.jsch.Session;
import com.kuzank.escluster.common.bean.AppAuth;
import com.kuzank.escluster.common.bean.AuthEnum;
import com.kuzank.escluster.common.bean.Constants;
import com.kuzank.escluster.common.bean.OperateStatus;
import com.kuzank.escluster.common.helper.JsonResponse;
import com.kuzank.escluster.entity.LinuxConnEntity;
import com.kuzank.escluster.mapper.entity.ESNodeEntity;
import com.kuzank.escluster.mapper.entity.UserEntity;
import com.kuzank.escluster.service.ESNodeService;
import com.kuzank.escluster.util.CheckUtil;
import com.kuzank.escluster.util.SSHUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 */
@Controller
@AppAuth(role = AuthEnum.Observation)
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private ESNodeService esNodeService;

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "operation";
    }

    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json"})
    @ResponseBody
    public List<ESNodeEntity> info(HttpServletRequest request) throws Exception {

        UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        List<ESNodeEntity> entityList = esNodeService.findByBeloneAppId(userEntity.getBeloneAppId());

        return entityList;
    }

    /**
     * TODO 后续功能 ESNote表中添加一个字段 isConfig，用来判断是否需要写入 unicateHost 信息
     */
    @RequestMapping(value = "/start", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public JsonResponse start(@RequestParam String _host, @RequestParam String _username,
                              @RequestParam String _password, @RequestParam int id) throws Exception {

        // 验证远程主机参数信息
        if (CheckUtil.NotEmpty(_host, _username, _password) != OperateStatus.SUCCESS) {
            return new JsonResponse(OperateStatus.PARAM_EMPTY);
        }

        // 验证是否可以连接远程主机
        LinuxConnEntity linuxConnEntity = new LinuxConnEntity(_host, LinuxConnEntity.defaultPort, _username, _password);
        Session session = SSHUtil.getSession(linuxConnEntity);
        if (!session.isConnected()) {
            return new JsonResponse(OperateStatus.LINUX_CANT_CONNECT);
        }

        // 执行启动
        ESNodeEntity entity = esNodeService.findById(id);
        if (entity == null) {
            return new JsonResponse(OperateStatus.CLUSTER_NODE_NO_EXIST);
        }
        String shellPath = "/usr/elasticsearch/es" + entity.getTcpPort() + "/shell/run_es.sh";
        SSHUtil.executeCommand(session, "chmod a+x " + shellPath);
        SSHUtil.executeCommand(session, shellPath + "  " + entity.getTcpPort());
//
//        boolean isRunning = ElasticsearchUtil.isNodeRunning(XMLPATH, _host, _tcpPort);

//        session.disconnect();
        return JsonResponse.SUCCESS;
    }


    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> stop(@RequestParam String _host, @RequestParam String _username,
                             @RequestParam String _password, @RequestParam String tcpPort) {

        Map<String, Object> map = new HashMap<String, Object>();
        LinuxConnEntity linuxConnEntity = new LinuxConnEntity(_host, LinuxConnEntity.defaultPort, _username, _password);

        Session session = SSHUtil.getSession(linuxConnEntity);

        String shellPath = "/usr/elasticsearch/es" + tcpPort + "/shell/stop_es.sh";
        SSHUtil.executeCommand(session, "chmod a+x " + shellPath);
        String result = SSHUtil.executeCommand(session, shellPath + "  " + tcpPort);
        result = result.replace("\n", "");

        if (result != null && result.equals("NONE")) {  // 节点没有在运行
            map.put("result", "false");
            map.put("msg", "节点 (" + _host + ":" + tcpPort + ") " + "并没有在运行！");
        } else if (result != null && result.equals("SUCCESS")) {
            map.put("result", "true");
            map.put("msg", "节点 (" + _host + ":" + tcpPort + ") " + "停止成功！");
        }
        session.disconnect();
        return map;
    }

}
