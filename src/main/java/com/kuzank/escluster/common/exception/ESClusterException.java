package com.kuzank.escluster.common.exception;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
public class ESClusterException extends Exception {

    public ESClusterException() {
        super((String) null);
    }

    public ESClusterException(String msg) {
        super(msg);
    }

    public ESClusterException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
