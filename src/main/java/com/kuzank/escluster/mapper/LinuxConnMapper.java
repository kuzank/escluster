package com.kuzank.escluster.mapper;

import com.kuzank.escluster.mapper.entity.LinuxConnEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
@Mapper
public interface LinuxConnMapper extends BaseMapper<LinuxConnEntity> {

    public static final String COLLECTION = "";

    @Insert("INSERT INTO LINUXCONN(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);


}
