package com.jubenwu.guanlixitong.enums;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */

public enum ResultEnums {

    LOGIN_SUCCESS(200,"登录成功"),
    REGISTER_SUCCESS(201,"注册成功"),
    ADD_FORM_SUCCESS(202,"上报成功"),
    QUERY_SUCCESS(203,"查询成功"),
    UPDATE_SUCCESS(204,"更新成功"),
    DELETE_SUCCESS(205,"删除成功"),



    LOGIN_FAILED(100,"登录失败，未知错误"),
    PASSWORD_ERROR(101,"密码错误"),
    ACCOUNT_EXIST(102,"账号已存在"),
    ADD_FORM_FAILED(103,"上报失败，请重试。如果多次失败，请联系管理员:错误代码203"),
    UPDATE_FAILED(104,"更新失败"),
    DELETE_FAILED(105,"上报失败，请重试。如果多次失败，请联系管理员:错误代码203"),



    UNKOWN_ERROR(900,"未知错误");



    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private Integer code;
    private String message;

    ResultEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
