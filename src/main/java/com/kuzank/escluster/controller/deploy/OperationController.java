package com.kuzank.escluster.controller.deploy;

import com.jcraft.jsch.Session;
import com.kuzank.escluster.common.bean.AppAuth;
import com.kuzank.escluster.common.bean.AuthEnum;
import com.kuzank.escluster.entity.LinuxConnEntity;
import com.kuzank.escluster.util.SSHUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 */
@Controller
@AppAuth(role = AuthEnum.Observation)
@RequestMapping("/operation")
public class OperationController {

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "operation";
    }

    @RequestMapping(value = "/start_es_remote", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> start_es(@RequestParam String _host, @RequestParam String _username,
                                 @RequestParam String _password, @RequestParam String _tcpPort) {
        Map<String, Object> map = new HashMap<String, Object>();
        LinuxConnEntity linuxConnEntity = new LinuxConnEntity(_host, LinuxConnEntity.defaultPort, _username, _password);

//        String unicastHost = NodesXmlUtil.getUnicastHostsByXml(XMLPATH);
//
//        Session session = SSHUtil.getSession(linuxConnEntity);

//        map.put("result", "false");
//        if (!session.isConnected()) {
//            map.put("msg", "无法与远端服务器建立连接，请检查服务器IP地址、用户名和密码是否正确！");
//            return map;
//        }
//        if (!NodesXmlUtil.hasUnicast_hosts(XMLPATH, _host, _tcpPort)) {
//            String command = "echo \'discovery.zen.ping.unicast.hosts: [" + unicastHost + "]\' >> " + "/usr/elasticsearch/es" + tcpPort + "/config/elasticsearch.yml";
//            SSHUtil.executeCommand(session, command);
//            boolean setResult = NodesXmlUtil.setUnicast_hosts(XMLPATH, _host, _tcpPort, unicastHost);
//            if (setResult == false) {
//                map.put("msg", "将集群节点信息写入配置文件 " + _host + ":" + _tcpPort + " 中出现错误！");
//                return map;
//            }
//        }
//        String shellPath = "/usr/elasticsearch/es" + _tcpPort + "/shell/run_es.sh";
//        SSHUtil.executeCommand(session, "chmod a+x " + shellPath);
//        SSHUtil.executeCommand(session, shellPath + "  " + _tcpPort);
//
//        boolean isRunning = ElasticsearchUtil.isNodeRunning(XMLPATH, _host, _tcpPort);
//
//        if (isRunning == true) {
//            map.put("msg", "节点 (" + _host + ":" + _tcpPort + ") " + "启动成功！");
//            map.put("result", "true");
//        } else {
//            map.put("msg", "节点 (" + _host + ":" + _tcpPort + ") " + "启动失败！");
//        }
//        session.disconnect();
        return map;
    }


    @RequestMapping(value = "/stop_es_remote", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> stop_es(@RequestParam String _host, @RequestParam String _username,
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
