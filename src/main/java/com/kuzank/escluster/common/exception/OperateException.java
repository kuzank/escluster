package com.kuzank.escluster.common.exception;

import com.kuzank.escluster.common.bean.OperateStatus;

/**
 * <p>Description: </p>
 */
public class OperateException extends Exception {

    private OperateStatus status;
    private Class clazz;

    public OperateException() {
        super();
    }

    public OperateException(OperateStatus status) {
        super(status.getDetail());
        this.status = status;
    }

    public OperateException(OperateStatus status, Class clazz) {
        super(status.getDetail());
        this.status = status;
        this.clazz = clazz;
    }

    public OperateException(OperateStatus status, String message) {
        super(message);
        this.status = status;
    }

    public OperateException(OperateStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public OperateException(OperateStatus status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    protected OperateException(OperateStatus status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": status=>" + (status == null ? "haven't status" : status) + " ,message=>" + message) : s;
    }

    public OperateStatus getStatus() {
        return this.status;
    }

    public Class getClazz() {
        return this.clazz;
    }
}

