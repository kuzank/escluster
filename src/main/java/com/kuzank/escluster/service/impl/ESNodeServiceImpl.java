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
    public String getUnicastHost(int beloneAppId) throws Exception {

        String result = null;
        List<ESNodeEntity> esNodeEntities = esNodeMapper.findByBeloneAppId(beloneAppId);

        if (esNodeEntities != null && esNodeEntities.size() > 0) {
            result = esNodeEntities.get(0).getHost() + ":" + esNodeEntities.get(0).getTcpPort();
            for (int i = 1; i < esNodeEntities.size(); i++) {
                ESNodeEntity item = esNodeEntities.get(i);
                result = result + "," + item.getHost() + ":" + item.getTcpPort();
            }
        }
        return result;
    }

    @Override
    public ESNodeEntity findById(int id) throws Exception {
        return esNodeMapper.findById(id, ESNodeMapper.COLLECTION);
    }

    @Override
    public List<ESNodeEntity> findByBeloneAppId(int beloneAppId) throws Exception {

        return esNodeMapper.findByBeloneAppId(beloneAppId);
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
