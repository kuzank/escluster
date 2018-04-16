package com.kuzank.escluster.common.bean;

/**
 * <p>Description: </p>
 *
 * @author kuzan
 * @since 2018-04-15
 */
public enum OperateStatus {
    SUCCESS(200, "操作成功"),
    CAPTCHA_ERROR(310, "验证码错误"),
    DATABASE_EXIST(311, "数据库已经存在"),
    TABLE_EXIST(312, "表已经存在"),
    FIELD_EXIST(313, "字段已经存在"),
    FUNCTION_EXIST(314, "方法已经存在"),
    VIEW_EXIST(315, "视图已经存在"),
    REPORT_EXIST(316, "报表已经存在"),

    PARAMS_DEFICIENCY(325, "参数缺失"),
    NULL_OF_USER_ID(326, "用户id不存在"),
    NULL_OF_OLD_PASSWORD(327, "缺失旧密码参数"),
    NULL_OF_PASSWORD(328, "密码不能为空"),
    NULL_OF_MOBILE(329, "缺失手机号"),
    NULL_OF_ACCOUNT(330, "缺失账号"),
    NULL_OF_USER_NAME(331, "用户名不能为空"),
    NOT_FOUND_FORM(411, "未找到该表"),
    LOGIN_ERROR(402, "登陆出错"),
    NOT_LOGIN_IN(403, "未登录"),
    LOGIN_FAIL(405, "登陆失败，密码错误或用户名错误"),

    NOT_FOUND_SELECT_ITEM(413, "未找到选择项"),
    NOT_FOUND_USER(414, "用户不存在"),
    ENCRYPT_KEYS_NOT_FOUND(415, "加密索引过期"),
    SYSTEM_ERROR(500, "系统内部错误"),
    CREDENTIAL_BINDING_ERROR(503, "凭证构建失败"),
    RUNTIME_PARALLEL_COMPUTE_ERROR(510, "并行计算出错"),
    COMPLETENESS_DATA_ERROR(562, "数据完整性错误"),
    CHECK_PASSWORD_NOT_MATCH(580, "密码不正确"),//与原始密码不匹配
    CHECK_PASSWORD_REPEAT_NOT_MATCH(581, "两次新密码输入不相同"),
    CHECK_PASSWORD_LENGTH_ERROR(582, "新密码不能少于6位"),
    CHECK_MOBILE_LENGTH_ERROR(582, "手机号错误"),
    CHECK_INVALID_USER_ID(586, "非法用户id"),
    CHECK_SQL_EXCEPTION(587, "sql执行错误"),

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

