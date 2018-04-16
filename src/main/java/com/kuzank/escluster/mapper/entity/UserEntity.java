package com.kuzank.escluster.mapper.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = -6455905628615377123L;

    private String name;
    private String account;
    private String password;
    private String mobile;
    private String email;

    private String beloneAppId;   // 属于哪一个应用
    private String appAuth;       // 用户在应用中的权限
}
