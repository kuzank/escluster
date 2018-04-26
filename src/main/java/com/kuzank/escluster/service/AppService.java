package com.kuzank.escluster.service;

import com.kuzank.escluster.mapper.entity.AppEntity;

import java.util.List;

public interface AppService {

    public int insert(AppEntity appEntity) throws Exception;

    List<AppEntity> findByClusterName(String clusterName) throws Exception;

    List<AppEntity> findByUserId(int userid) throws Exception;
}
