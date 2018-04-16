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
public class LinuxConnEntity extends BaseEntity {

    private static final long serialVersionUID = -6455905628615377123L;

    private String username;
    private String password;
    private String host;
    private int port;

}
