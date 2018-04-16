package com.kuzank.escluster.common.bean;

import com.kuzank.escluster.common.exception.ESClusterException;

/**
 * <p>Description: 外部对应用的访问权限控制
 * -- Observation 用户的权限是观察者，可以进入本应用，对标记观察者权限的资源可以进行查看
 * --        CRUD 用户的权限是 CRUD ，可以进入本应用，拥有对标记 CRUD 的资源进行增删查改
 * --    AppAdmin 应用管理员，在 CRUD 的基础上为本应用添加用户
 * --    SysAdmin 系统管理员，在 AppAdministrator 的基础上添加对 AppAdministrator 用户的管理功能(增、删、禁用)
 * </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
public enum AuthEnum {

    Observation((byte) 0),
    CRUD((byte) 1),
    AppAdmin((byte) 2),
    SysAdmin((byte) 3);

    public static final AuthEnum DEFAULT = Observation;
    private byte id;

    AuthEnum(byte b) {
        this.id = b;
    }

    public byte id() {
        return this.id;
    }

    public static AuthEnum fromId(byte id) throws ESClusterException {
        if (id == 0) {
            return Observation;
        } else if (id == 1) {
            return CRUD;
        } else if (id == 2) {
            return AppAdmin;
        } else if (id == 3) {
            return SysAdmin;
        } else {
            throw new ESClusterException("No search type for [" + id + "]");
        }
    }

    public static AuthEnum fromString(String searchType) throws ESClusterException {
        if (searchType == null) {
            return DEFAULT;
        } else if ("Observation".equals(searchType)) {
            return Observation;
        } else if ("CRUD".equals(searchType)) {
            return CRUD;
        } else if ("AppAdmin".equals(searchType)) {
            return AppAdmin;
        } else if ("SysAdmin".equals(searchType)) {
            return SysAdmin;
        } else {
            throw new ESClusterException("No search type for [" + searchType + "]");
        }
    }
}
