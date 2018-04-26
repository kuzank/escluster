package com.kuzank.escluster.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

/**
 * <p>Description: </p>
 */
@Mapper
public interface BaseMapper<T> {

    public static final String S_Base_Collection = "s_base_collection";

    public static final String Collection = "collection";
    public static final String ID = "id";

    public static final String Field_Name = "fieldName";
    public static final String Field_Value = "fieldValue";


    @Select("${_sql}")
    List<T> findBySql(@Param("_sql") String _sql);


    @Update("${_sql}")
    int executeBySql(@Param("_sql") String _sql);


    @Update("update ${paramMap.collection} set ${paramMap." + Field_Name + "} = ${paramMap." + Field_Value + "} where id=${paramMap.id}")
    int updateByMapInt(@Param("paramMap") HashMap<String, String> paramMap);


    @Update("update ${paramMap.collection} set ${paramMap." + Field_Name + "} = #{paramMap." + Field_Value + "} where id=${paramMap.id}")
    int updateByMapString(@Param("paramMap") HashMap<String, String> paramMap);


    @Select("select * from ${collection} where deleted = 'false'")
    List<T> findAll(@Param("collection") String collection);

}
