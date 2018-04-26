package com.kuzank.escluster.service.impl;

import com.kuzank.escluster.common.bean.OperateStatus;
import com.kuzank.escluster.common.exception.OperateException;
import com.kuzank.escluster.mapper.UserMapper;
import com.kuzank.escluster.mapper.entity.UserEntity;
import com.kuzank.escluster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.kuzank.escluster.common.helper.Preconditions.checkNotNull;

/**
 * <p>Description: </p>
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity createUser(UserEntity user) throws Exception {
        int result = userMapper.insert(user);
        return user;
    }

    @Override
    public void updateUser(UserEntity user) throws Exception {

    }

    @Override
    public void changePassword(Integer id, String oldPassword, String newPassword) throws OperateException {

        checkNotNull(id, OperateStatus.NULL_OF_USER_ID);
        checkNotNull(oldPassword, OperateStatus.NULL_OF_OLD_PASSWORD);
        checkNotNull(newPassword, OperateStatus.NULL_OF_PASSWORD);

        UserEntity userEntity = userMapper.findById(String.valueOf(id));

        if (userEntity == null && oldPassword.equals(userEntity.getPassword())) {
            userMapper.updatePassword(String.valueOf(id), newPassword);
        }
    }

    @Override
    public void deleteUser(int id) throws Exception {

        int result = userMapper.softDeleteById(String.valueOf(id));
    }

    @Override
    public UserEntity findUserById(int id) throws Exception {

        return userMapper.findById(String.valueOf(id));
    }


    @Override
    public UserEntity findUserByAccount(String account) throws OperateException {
        checkNotNull(account, OperateStatus.NULL_OF_ACCOUNT);

        return userMapper.findByAccount(account);
    }


    @Override
    public boolean validateUserByAccount(String account, String password) throws OperateException {
        checkNotNull(account, OperateStatus.NULL_OF_ACCOUNT);
        checkNotNull(password, OperateStatus.NULL_OF_PASSWORD);

        UserEntity userEntity = userMapper.findByAccountAndPassword(account, password);

        if (userEntity == null) {
            return false;
        }

        return true;
    }
}
