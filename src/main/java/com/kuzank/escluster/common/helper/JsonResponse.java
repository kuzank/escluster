package com.kuzank.escluster.common.helper;

import com.kuzank.escluster.common.bean.OperateStatus;

/**
 * Created by Rogers on 15-6-11.
 */
public class JsonResponse {
    public static final JsonResponse OK = new JsonResponse(OperateStatus.SUCCESS);
    private int status;
    private String message;
    private Object data;

    public JsonResponse() {
    }

    public JsonResponse(OperateStatus operateStatus) {
        this.status = operateStatus.getValue();
        this.message = operateStatus.getDetail();
    }

    public JsonResponse(OperateStatus operateStatus, Object data) {
        this.status = operateStatus.getValue();
        this.message = operateStatus.getDetail();
        this.data = data;
    }

    public JsonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public JsonResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
