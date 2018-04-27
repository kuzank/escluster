package com.kuzank.escluster.service;

import com.kuzank.escluster.mapper.entity.ESNodeEntity;

import java.util.List;

/**
 * <p>Description: </p>
 */
public interface ESNodeService {

    public int insert(ESNodeEntity esNodeEntity) throws Exception;

    public String getUnicastHost(int beloneAppId) throws Exception;

    public List<ESNodeEntity> findByNodeName(int beloneAppId, String nodeName) throws Exception;

    public List<ESNodeEntity> findByHostAndTcpPort(int beloneAppId, String host, String tcpPort) throws Exception;
}
