package com.kuzank.escluster.mapper.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class ESNodeEntity extends BaseEntity {

    private static final long serialVersionUID = -6455905628615377123L;

    private String beloneAppId;   // 属于哪一个应用

    private String clusterName;
    private String nodeName;
    private String master;
    private String data;
    private String dataDir;
    private String host;
    private String httpPort;
    private String tcpPort;
    private String httpEnabled;  // only yes or no
    private String unicastHost;

}
