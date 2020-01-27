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
    LOGIN_FAILED(100,"登录失败，未知错误"),
    PASSWORD_ERROR(101,"密码错误");

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
