package com.kuzank.escluster.service.impl;

import com.kuzank.escluster.mapper.entity.AppEntity;
import com.kuzank.escluster.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AppServiceImpl implements AppService {

    @Autowired
    private AppService appService;

    @Override
    public int insert(AppEntity appEntity) throws Exception {

        List<AppEntity> appEntities = appService.findByUserId(appEntity.getCreatedBy());
        if (appEntities == null || appEntities.size() == 0)
            return appService.insert(appEntity);

        return 0;
    }

    @Override
    public List<AppEntity> findByClusterName(String clusterName) throws Exception {

        return appService.findByClusterName(clusterName);
    }

    @Override
    public List<AppEntity> findByUserId(int userid) throws Exception {

        return appService.findByUserId(userid);
    }
}
