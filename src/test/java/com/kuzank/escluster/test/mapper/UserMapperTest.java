package com.kuzank.escluster.test.mapper;

import com.kuzank.escluster.mapper.UserMapper;
import com.kuzank.escluster.mapper.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    @Rollback
    public void insert() throws Exception {

        UserEntity user = new UserEntity();
        user.setBeloneAppId("0");
        user.setDeleted(Boolean.FALSE.toString());

        user.setName("kuzank");
        user.setAccount("kuzank");
        user.setPassword("kuzank");
        user.setAppAuth("SysAdmin");
        user.setMobile("15219378950");
        user.setEmail("754109648@qq.com");
        user.setCreatedBy(1);

        System.out.println(user);

        UserEntity userEntity = userMapper.findByAccount(user.getAccount());
        if (userEntity == null) {
            int result = userMapper.insert(user);
            System.out.println("result : " + result);
        } else {
            throw new Exception("Account Exist");
        }
    }


    @Test
    @Rollback
    public void findById() {

        UserEntity userEntity = userMapper.findById("3");
        System.out.println(userEntity);
    }


    @Test
    @Rollback
    public void findAllByBeloneAppId() {

        List<UserEntity> userEntitys = userMapper.findAllByBeloneAppId("0");
        display(userEntitys);
    }


    @Test
    @Rollback
    public void findByAccount() {
        UserEntity userEntity = userMapper.findByAccount("kuzank");
        System.out.println(userEntity);
    }


    @Test
    @Rollback
    public void findByAccountAndPassword() {
        UserEntity userEntity = userMapper.findByAccountAndPassword("kuzank", "kuzank");
        System.out.println(userEntity);
    }


    @Test
    @Rollback
    public void softDeleteById() {
        int result = userMapper.softDeleteById("4");
        System.out.println("result : " + result);
    }


    @Test
    @Rollback
    public void updatePassword() {
        int result = userMapper.updatePassword("4", "kuzan");
        System.out.println("result : " + result);
    }


    @Test
    @Rollback
    public void updateUser() {

        UserEntity user = new UserEntity();
        user.setId(4);
        user.setBeloneAppId("0");

        user.setName("kuzank");
        user.setAppAuth("SysAdmin");
        user.setMobile("15219378950");
        user.setEmail("754109648@qq.com");

        user.setUpdatedBy(1);
        user.setDescription("updateUser test");

        int result = userMapper.updateUser(user);
        System.out.println("result : " + result);
    }


    public static void display(List<UserEntity> userEntities) {

        System.out.println("  size : " + userEntities.size());
        System.out.println("  List : " + userEntities);

        for (UserEntity user : userEntities)
            System.out.println(user);
    }
}
