package com.kuzank.escluster.controller.deploy;

import com.jcraft.jsch.Session;
import com.kuzank.escluster.common.bean.AppAuth;
import com.kuzank.escluster.common.bean.AuthEnum;
import com.kuzank.escluster.common.bean.OperateStatus;
import com.kuzank.escluster.common.helper.JsonResponse;
import com.kuzank.escluster.entity.LinuxConnEntity;
import com.kuzank.escluster.util.CheckUtil;
import com.kuzank.escluster.util.SSHUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Description: 部署安装 ElasticSearch 应用的 Controller</p>
 */
@Controller
@AppAuth(role = AuthEnum.Observation)
@RequestMapping("/install")
public class InstallController {

    private final String ES_NAME = "elasticsearch-5.4.3";
    private final String ES_TMP_DIR = "/tmp/elasticsearch-5.4.3";
    private final String ES_INSTALL_SHELL_DIR = ES_TMP_DIR + "/shell/install_es.sh";

    //    private final String XMLPATH = SHELL_DIR + ElasticsearchUtil.XMLNAME;
    private final String XMLPATH = "";


    private static Logger logger = Logger.getLogger(InstallController.class);


    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "install";
    }

    @RequestMapping(value = "/execute", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public JsonResponse execute(@RequestParam String _host, @RequestParam String _username, @RequestParam String _password,
                                @RequestParam String _nodeName, @RequestParam String _nodeMaster,
                                @RequestParam String _noteData, @RequestParam String _memory,
                                @RequestParam String _tcpPort, @RequestParam String _httpPort) {

        // TODO 判断 clusterName 是否已存在

        LinuxConnEntity linuxConnEntity = new LinuxConnEntity(_host, LinuxConnEntity.defaultPort, _username, _password);

        // 验证是否可以连接远程主机
        OperateStatus checkStatus = SSHUtil.isLinuxConnected(linuxConnEntity);
        if (checkStatus != OperateStatus.SUCCESS) {
            return new JsonResponse(checkStatus);
        }
        // 验证 ElasticSearch 参数
        if (CheckUtil.NotEmpty(_nodeMaster, _nodeMaster, _noteData, _memory, _tcpPort, _httpPort) != OperateStatus.SUCCESS)
            return new JsonResponse(OperateStatus.PARAM_EMPTY);

        Session session = SSHUtil.getSession(linuxConnEntity);

        // 判断 tcpPort 是否被占用
        if (SSHUtil.isPortInUse(session, _tcpPort)) {
            return new JsonResponse(OperateStatus.LINUX_TCP_PORT_INUSE);
        }
        // 判断 httpPort 是否被占用
        if (SSHUtil.isPortInUse(session, _httpPort)) {
            return new JsonResponse(OperateStatus.LINUX_HTTP_PORT_INUSE);
        }

        final String SHELL_DIR = getClass().getClassLoader().getResource("/").getPath();

        // 将 ElasticSearch 压缩文件传送到远程 Linux 上并解压到 /tmp 目录下
        SSHUtil.sendDir(session, SHELL_DIR + ES_NAME + ".tar.gz", "/tmp");
        SSHUtil.executeCommand(session, "tar -zxf " + ES_TMP_DIR + ".tar.gz -C /tmp");

        // 安装和配置 ElasticSearch 运行参数
//        SSHUtil.executeCommand(session, "chmod a+x " + ES_INSTALL_SHELL_DIR);
//        SSHUtil.executeCommand(session, ES_INSTALL_SHELL_DIR + " " + ES_TMP_DIR + " " + clusterName + " "
//                + _nodeName + " " + _tcpPort + " " + _httpPort + " " + _memory + " " + httpEnabled + " " + nodeMaster + " " + noteData + " " + host);

        // 删除临时文件
//        SSHUtil.executeCommand(session, "rm -f " + ES_TMP_DIR + ".tar.gz");
//        SSHUtil.executeCommand(session, "rm -rf " + ES_TMP_DIR);

        // 将节点信息写入xml文件
//        ESNote esNote = new ESNote(nodeName, host, httpPort, tcpPort, httpEnabled, nodeMaster, noteData, "");
//        NodesXmlUtil.writeNode(XMLPATH, esNote);

        session.disconnect();
//        map.put("result", "true");
//        map.put("msg", "Install Elasticsearch In /usr/elasticsearch/es" + tcpPort + " Successfully.");

        return JsonResponse.OK;
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

}
