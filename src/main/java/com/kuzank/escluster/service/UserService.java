package com.kuzank.escluster.service;

import com.kuzank.escluster.common.exception.OperateException;
import com.kuzank.escluster.mapper.entity.UserEntity;

/**
 * <p>Description: </p>
 */
public interface UserService {

    public UserEntity createUser(UserEntity user) throws Exception;

    public void updateUser(UserEntity user) throws Exception;

    public void changePassword(Integer userId, String oldPassword, String newPassword) throws Exception;

    public void deleteUser(int userId) throws Exception;

    public UserEntity getUserById(int userId) throws Exception;

    public UserEntity getUserByAccount(String account) throws Exception;

    boolean validateUserByAccount(String account, String password) throws OperateException;

}
