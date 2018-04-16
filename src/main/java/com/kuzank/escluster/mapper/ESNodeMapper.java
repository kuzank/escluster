package com.kuzank.escluster.mapper;

import com.kuzank.escluster.mapper.entity.ESNodeEntity;
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
public interface ESNodeMapper extends BaseMapper<ESNodeEntity> {

    public static final String COLLECTION = "esnode";

    @Insert("INSERT INTO " + COLLECTION + "(id, beloneAppId, deleted, clusterName, nodeName, master, data, " +
            "dataDir, host, httpPort, tcpPort, httpEnabled, unicastHost, createdBy, createdAt) " +
            "VALUES(null, ${node.beloneAppId}, #{node.deleted},#{node.clusterName}, #{node.nodeName}, #{node.master}, #{node.data}, " +
            "#{node.dataDir}, #{node.host}, #{node.httpPort}, #{node.tcpPort}, #{node.httpEnabled}, #{node.unicastHost}, ${node.createdBy}, now())")
    int insert(@Param("node") ESNodeEntity node);


    @Select("SELECT * FROM " + COLLECTION + " WHERE beloneAppId = ${beloneAppId} and nodeName = #{nodeName} and deleted = 'false'")
    ESNodeEntity findByBeloneAppIdAndNodeName(@Param("beloneAppId") String beloneAppId, @Param("nodeName") String nodeName);


}
