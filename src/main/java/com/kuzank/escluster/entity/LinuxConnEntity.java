package com.kuzank.escluster.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>Description: </p>
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
