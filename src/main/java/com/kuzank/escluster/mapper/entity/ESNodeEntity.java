package com.kuzank.escluster.mapper.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>Description: </p>
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class ESNodeEntity extends BaseEntity {

    private static final long serialVersionUID = -6455905628615377123L;

    private Integer beloneAppId;   // 属于哪一个应用

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
