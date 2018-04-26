package com.kuzank.escluster.service.impl;

import com.kuzank.escluster.mapper.ESNodeMapper;
import com.kuzank.escluster.mapper.entity.ESNodeEntity;
import com.kuzank.escluster.service.ESNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: </p>
 */
@Service
public class ESNodeServiceImpl implements ESNodeService {

    @Autowired
    private ESNodeMapper esNodeMapper;


    @Override
    public int insert(ESNodeEntity esNodeEntity) throws Exception {

        return esNodeMapper.insert(esNodeEntity);
    }

    @Override
    public List<ESNodeEntity> findByNodeName(int beloneAppId, String nodeName) throws Exception {

        return esNodeMapper.findByNodeName(beloneAppId, nodeName);
    }

    @Override
    public List<ESNodeEntity> findByHostAndTcpPort(int beloneAppId, String host, String tcpPort) throws Exception {

        return esNodeMapper.findByPortAndTcpPort(beloneAppId, host, tcpPort);
    }
}
