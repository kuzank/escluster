package com.kuzank.escluster.controller.deploy;

import com.jcraft.jsch.Session;
import com.kuzank.escluster.common.bean.AppAuth;
import com.kuzank.escluster.common.bean.AuthEnum;
import com.kuzank.escluster.common.bean.Constants;
import com.kuzank.escluster.common.bean.OperateStatus;
import com.kuzank.escluster.common.helper.JsonResponse;
import com.kuzank.escluster.entity.LinuxConnEntity;
import com.kuzank.escluster.mapper.entity.AppEntity;
import com.kuzank.escluster.mapper.entity.ESNodeEntity;
import com.kuzank.escluster.mapper.entity.UserEntity;
import com.kuzank.escluster.service.AppService;
import com.kuzank.escluster.service.ESNodeService;
import com.kuzank.escluster.util.CheckUtil;
import com.kuzank.escluster.util.SSHUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Description: 部署安装 ElasticSearch 应用的 Controller</p>
 */
@Controller
@AppAuth(role = AuthEnum.Observation)
@RequestMapping("/install")
public class InstallController {

    @Autowired
    private AppService appService;
    @Autowired
    private ESNodeService esNodeService;

    private final String ES_NAME = "elasticsearch-5.4.3";
    private final String ES_TMP_Path = "/tmp/" + ES_NAME;
    private final String ES_Install_Shell_Path = ES_TMP_Path + "/shell/install_es.sh";
    private final String TarPath = getClass().getClassLoader().getResource("").getPath() + "static/tar/elasticsearch-5.4.3.tar.gz";

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
                                @RequestParam String _tcpPort, @RequestParam String _httpPort,
                                HttpServletRequest request) throws Exception {

        // 判断 clusterName 是否已存在
        String clusterName = null;
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        List<AppEntity> appEntitys = appService.findByUserId(userEntity.getId());
        if (appEntitys != null && appEntitys.size() > 0) {
            clusterName = appEntitys.get(0).getClusterName();
        }
        if (clusterName == null || clusterName.length() == 0 || "null".equals(clusterName)) {
            return new JsonResponse(OperateStatus.CLUSTER_NAME_EMPTY);
        }
        // 验证 ElasticSearch 参数
        if (CheckUtil.NotEmpty(_nodeMaster, _nodeMaster, _noteData, _memory, _tcpPort, _httpPort) != OperateStatus.SUCCESS)
            return new JsonResponse(OperateStatus.PARAM_EMPTY);

        // 判断是否存在相同节点名字
        List<ESNodeEntity> esNodeEntities = esNodeService.findByNodeName(appEntitys.get(0).getId(), _nodeName);
        if (esNodeEntities != null && esNodeEntities.size() > 0) {
            return new JsonResponse(OperateStatus.CLUSTER_CONTAIN_SAME_NODENAME);
        }

        // 判断是否存在相同 host && tcpPort
        esNodeEntities = esNodeService.findByHostAndTcpPort(appEntitys.get(0).getId(), _host, _tcpPort);
        if (esNodeEntities != null && esNodeEntities.size() > 0) {
            return new JsonResponse(OperateStatus.CLUSTER_CONTAIN_SAME_IP_TCPPORT);
        }

        // 验证远程主机参数信息
        if (CheckUtil.NotEmpty(_host, _username, _password) != OperateStatus.SUCCESS) {
            return new JsonResponse(OperateStatus.PARAM_EMPTY);
        }

        LinuxConnEntity linuxConnEntity = new LinuxConnEntity(_host, LinuxConnEntity.defaultPort, _username, _password);
        Session session = SSHUtil.getSession(linuxConnEntity);
        if (!session.isConnected()) {
            return new JsonResponse(OperateStatus.LINUX_CANT_CONNECT);
        }

        // 验证是否可以连接远程主机
        OperateStatus checkStatus = SSHUtil.isLinuxConnected(linuxConnEntity);
        if (checkStatus != OperateStatus.SUCCESS) {
            return new JsonResponse(checkStatus);
        }

        // 判断 tcpPort 是否被占用
        if (SSHUtil.isPortInUse(session, _tcpPort)) {
            return new JsonResponse(OperateStatus.LINUX_TCP_PORT_INUSE);
        }
        // 判断 httpPort 是否被占用
        if (SSHUtil.isPortInUse(session, _httpPort)) {
            return new JsonResponse(OperateStatus.LINUX_HTTP_PORT_INUSE);
        }
        // 判断远程主机的目录是否存在
        if (SSHUtil.hasDir(session, "")) {
            return new JsonResponse(OperateStatus.LINUX_DIR_USED);
        }

        // 将 ElasticSearch 压缩文件传送到远程 Linux 上并解压到 /tmp 目录下
        SSHUtil.sendDir(session, TarPath, "/tmp");
        SSHUtil.executeCommand(session, "tar -zxf " + ES_TMP_Path + ".tar.gz -C /tmp");

        // 安装和配置 ElasticSearch 运行参数
        SSHUtil.executeCommand(session, "chmod a+x " + ES_Install_Shell_Path);
        SSHUtil.executeCommand(session, ES_Install_Shell_Path + " " + ES_TMP_Path + " " + clusterName + " "
                + _nodeName + " " + _tcpPort + " " + _httpPort + " " + _memory + " yes " + _nodeMaster + " " + _noteData + " " + _host);

        // 删除临时文件
//        SSHUtil.executeCommand(session, "rm -f " + ES_TMP_Path + ".tar.gz");
//        SSHUtil.executeCommand(session, "rm -rf " + ES_TMP_Path);

        // 将节点信息写入xml文件
//        ESNote esNote = new ESNote(nodeName, host, httpPort, tcpPort, httpEnabled, nodeMaster, noteData, "");
//        NodesXmlUtil.writeNode(XMLPATH, esNote);

        session.disconnect();
//        map.put("result", "true");
//        map.put("msg", "Install Elasticsearch In /usr/elasticsearch/es" + tcpPort + " Successfully.");

        return JsonResponse.SUCCESS;
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
