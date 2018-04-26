package com.kuzank.escluster.common.bean;

/**
 * <p>Description: </p>
 */
public enum OperateStatus {

    SUCCESS(200, "操作成功"),
    FALSE(400, "操作失败"),

    NULL_OF_USER_ID(300, "用户id不存在"),
    NULL_OF_OLD_PASSWORD(301, "用户不存在"),
    NULL_OF_PASSWORD(302, "密码不能为空"),
    NULL_OF_ACCOUNT(303, "缺失账号"),
    NOT_LOGIN_IN(304, "未登录"),
    LOGIN_FAIL(305, "登陆失败，密码错误或用户名错误"),

    PARAM_EMPTY(320, "参数信息不全，请求填写完整所有参数信息"),
    PARAM_NO_ALLOW(321, "参数命名不合法"),
    CLUSTER_NAME_EMPTY(330, "系统未创建集群应用"),
    CLUSTER_NAME_NO_ALLOW(331, "集群应用命名不合法"),

    LINUX_CANT_CONNECT(411, "连接信息出错,无法连接远程服务器"),
    LINUX_GET_MEMORY_ERROE(412, "连接远程服务器成功,但无法获取服务器内存信息"),
    LINUX_HTTP_PORT_INUSE(420, "远程主机的 http 端口本占用，请选择其他端口"),
    LINUX_TCP_PORT_INUSE(421, "远程主机的 tcp 端口被占用，请选择其他端口"),
    LINUX_DIR_USED(423, "远程主机的目录被占用"),

    SYSTEM_ERROR(500, "系统内部错误"),

    PERMISSIONS_EXCEPTION(601, "权限错误"),;

    private int value;
    private String detail;

    OperateStatus(int value, String detail) {
        this.value = value;
        this.detail = detail;
    }

    public int getValue() {
        return this.value;
    }

    public String getDetail() {
        return this.detail;
    }

    public String allDetail() {
        return this.name() + "(" + this.value + ", " + this.detail + ")";
    }
}

