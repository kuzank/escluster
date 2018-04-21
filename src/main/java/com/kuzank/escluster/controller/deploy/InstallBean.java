package com.kuzank.escluster.controller.deploy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>Description: 保存 部署安装 前端请求的数据</p>
 *
 * @author kuzan
 * @since 2018-04-18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InstallBean {

    private String _host;
    private String _username;
    private String _password;

    private String _nodeName;
    private String _nodeMaster;
    private String _noteData;
    private String _memory;

    private String _tcpPort;
    private String _httpPort;

    private String _httpEnabled;

}