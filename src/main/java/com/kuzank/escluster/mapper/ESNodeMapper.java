package com.kuzank.escluster.mapper;

import com.kuzank.escluster.mapper.entity.ESNodeEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>Description: </p>
 */
@Mapper
public interface ESNodeMapper extends BaseMapper<ESNodeEntity> {

    public static final String COLLECTION = "esnode";

    @Insert("INSERT INTO " + COLLECTION + "(id, beloneAppId, deleted, nodeName, master, data, " +
            "dataDir, host, httpPort, tcpPort, httpEnabled, unicastHost, createdBy, createdAt) " +
            "VALUES(null, ${node.beloneAppId}, 'false', #{node.nodeName}, #{node.master}, #{node.data}, " +
            "#{node.dataDir}, #{node.host}, #{node.httpPort}, #{node.tcpPort}, 'yes', #{node.unicastHost}, ${node.createdBy}, now())")
    int insert(@Param("node") ESNodeEntity node);

    @Select("SELECT * FROM " + COLLECTION + " WHERE beloneAppId = ${beloneAppId} AND deleted = 'false'")
    List<ESNodeEntity> findByBeloneAppId(@Param("beloneAppId") int beloneAppId);

    @Select("SELECT * FROM " + COLLECTION + " WHERE beloneAppId = ${beloneAppId} AND nodeName = #{nodeName} AND deleted = 'false'")
    List<ESNodeEntity> findByNodeName(@Param("beloneAppId") int beloneAppId, @Param("nodeName") String nodeName);

    @Select("SELECT * FROM " + COLLECTION + " WHERE beloneAppId = ${beloneAppId} AND host = #{host} AND tcpPort = #{tcpPort}")
    List<ESNodeEntity> findByPortAndTcpPort(@Param("beloneAppId") int beloneAppId,
                                            @Param("host") String host,
                                            @Param("tcpPort") String tcpPort);

}
