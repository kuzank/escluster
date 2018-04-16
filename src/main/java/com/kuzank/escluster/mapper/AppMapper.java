package com.kuzank.escluster.mapper;

import com.kuzank.escluster.mapper.entity.AppEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
@Mapper
public interface AppMapper extends BaseMapper<AppMapper> {

    public static final String COLLECTION = "s_app";

    @Insert("INSERT INTO " + COLLECTION +
            "(`id`, `deleted`, `clusterName`, `createdBy`, `createdAt`, `description`) " +
            "VALUES" +
            "(null, #{appEntity.deleted}, #{appEntity.clusterName}, ${appEntity.createdBy} ,now() ,#{appEntity.description})")
    int insert(@Param("appEntity") AppEntity appEntity);

    @Select("select * from " + COLLECTION + " where clusterName=#{clusterName} and deleted = 'false'")
    AppEntity findByClusterName(@Param("clusterName") String clusterName);
}
