package com.kuzank.escluster.mapper;

import com.kuzank.escluster.mapper.entity.AppEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("SELECT * FROM " + COLLECTION + " WHERE clusterName=#{clusterName} AND deleted = 'false'")
    List<AppEntity> findByClusterName(@Param("clusterName") String clusterName);

    @Select("SELECT * FROM " + COLLECTION + " a WHERE a.createdBy = ${userid} AND a.deleted = 'false'")
    List<AppEntity> findByUserId(@Param("userid") int userid);
}
