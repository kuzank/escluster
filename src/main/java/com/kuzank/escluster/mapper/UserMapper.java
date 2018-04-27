package com.kuzank.escluster.mapper;

import com.kuzank.escluster.mapper.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>Description: </p>
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    public static final String COLLECTION = "s_user";

    @Insert("INSERT INTO " + COLLECTION +
            "(id, name, account, password, mobile, email, beloneAppId, appAuth, description, deleted, createdBy, createdAt) " +
            "VALUES(null, #{user.name}, #{user.account}, #{user.password}, #{user.mobile}, #{user.email}, #{user.beloneAppId}, " +
            "#{user.appAuth}, #{user.description}, #{user.deleted}, ${user.createdBy}, now())")
    int insert(@Param("user") UserEntity user);


    @Select("select * from " + COLLECTION + " where deleted = 'false' and beloneAppId = ${beloneAppId}")
    List<UserEntity> findAllByBeloneAppId(@Param("beloneAppId") String beloneAppId);


    @Select("select * from " + COLLECTION + " where account=#{account} and deleted = 'false'")
    UserEntity findByAccount(@Param("account") String account);


    @Select("select * from " + COLLECTION + " where account=#{account} and password=#{password} and deleted = 'false'")
    UserEntity findByAccountAndPassword(@Param("account") String account, @Param("password") String password);


    @Update("update " + COLLECTION + " set deleted = 'true' where id = ${id}")
    int softDeleteById(@Param("id") String id);


    @Update("update " + COLLECTION + " set password = #{password},updatedAt = now() where id = #{id}")
    int updatePassword(@Param("id") String id, @Param("password") String password);


    @Update("update " + COLLECTION + " set " +
            "name = #{user.name}, mobile = #{user.mobile}, email = #{user.email}, appAuth = #{user.appAuth}, " +
            "updatedBy = ${user.updatedBy}, updatedAt = now(), description = #{user.description} " +
            "where id = ${user.id}")
    int updateUser(@Param("user") UserEntity user);

}