package com.kuzank.escluster.entity;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@ToString(callSuper = true)
public class LinuxConnEntity {

    public static final int defaultPort = 22;

    private String host;
    private int port;
    private String username;
    private String password;

}
