package com.kuzank.escluster.service.impl;

import com.kuzank.escluster.mapper.AppMapper;
import com.kuzank.escluster.mapper.entity.AppEntity;
import com.kuzank.escluster.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: </p>
 */
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppMapper appMapper;

    @Override
    public int insert(AppEntity appEntity) throws Exception {

        List<AppEntity> appEntities = appMapper.findByUserId(appEntity.getCreatedBy());
        if (appEntities == null || appEntities.size() == 0)
            return appMapper.insert(appEntity);

        return 0;
    }

    @Override
    public List<AppEntity> findByClusterName(String clusterName) throws Exception {

        List<AppEntity> appEntities = appMapper.findByClusterName(clusterName);
        if (appEntities == null || appEntities.size() == 0)
            return null;
        return appEntities;
    }

    @Override
    public List<AppEntity> findByUserId(int userid) throws Exception {

        List<AppEntity> appEntities = appMapper.findByUserId(userid);
        if (appEntities == null || appEntities.size() == 0)
            return null;
        return appEntities;
    }
}
