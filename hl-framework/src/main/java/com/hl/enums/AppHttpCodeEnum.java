package com.hl.enums;

/**
 * @Author : hupo, 创建于:2023/3/12
 */
public enum AppHttpCodeEnum {
    SUCCESS(200,"操作成功"),
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503,"邮箱已存在"),
    REQUIRE_USERNAME(504,"需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    CONTENT_NOT_NULL(506, "内容不能为空"),
    FILE_TYPE_ERROR(507, "文件类型错误,只能是png或者jpg"),
    USER_INFO_ERROR(508, "用户信息不能为空");


    int code;
    String msg;

    AppHttpCodeEnum(int code, String errMsg) {
        this.code = code;
        this.msg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
