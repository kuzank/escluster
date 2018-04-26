package com.kuzank.escluster.test.mapper;

import com.kuzank.escluster.mapper.BaseMapper;
import com.kuzank.escluster.mapper.UserMapper;
import com.kuzank.escluster.mapper.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

/**
 * <p>Description: </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void findAll() {
        List<UserEntity> userList = userMapper.findAll(UserMapper.COLLECTION);
        display(userList);
    }


    @Test
    @Rollback
    public void findBySql() {

        String _sql = "select * from s_user";
        List<UserEntity> userEntities = userMapper.findBySql(_sql);

        display(userEntities);
    }

    @Test
    @Rollback
    public void executeBySql() {

        String _sql = "update " + UserMapper.COLLECTION + " set deleted = 'false'";
        int result = userMapper.executeBySql(_sql);

        System.out.println("result : " + result);
    }


    @Test
    @Rollback
    public void updateByMapInt() {

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put(BaseMapper.Collection, UserMapper.COLLECTION);
        paramMap.put(BaseMapper.ID, "2");
        paramMap.put(BaseMapper.Field_Name, "beloneAppId");
        paramMap.put(BaseMapper.Field_Value, "0");

        int result = userMapper.updateByMapInt(paramMap);
        System.out.println("result : " + result);
    }


    @Test
    @Rollback
    public void updateByMapString() {

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put(BaseMapper.Collection, UserMapper.COLLECTION);
        paramMap.put(BaseMapper.ID, "2");
        paramMap.put(BaseMapper.Field_Name, "deleted");
        paramMap.put(BaseMapper.Field_Value, "false");

        int result = userMapper.updateByMapString(paramMap);
        System.out.println("result : " + result);
    }


    public static void display(List<UserEntity> userEntities) {

        System.out.println("  size : " + userEntities.size());
        System.out.println("  List : " + userEntities);

        for (UserEntity user : userEntities)
            System.out.println(user);

    }


}
