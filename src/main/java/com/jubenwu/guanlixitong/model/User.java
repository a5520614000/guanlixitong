package com.jubenwu.guanlixitong.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Data
public class User implements Serializable {
    /**
    * id
    */
    private Integer id;

    /**
    * 账号
    */
    private String accountId;

    /**
    * 密码
    */
    private String password;

    /**
    * 该账号使用人的真实名字
    */
    private String name;

    /**
    * 该账号使用人的手机号码
    */
    private String phoneNumber;

    /**
    * 该账号使用人所属公司名称
    */
    private String companyName;

    /**
    * 该账号使用人的权限等级
    */
    private Integer level;

    /**
    * 该账号使用人权限等级的名称
    */
    private String levelName;

    /**
    * 该账号创建时间
    */
    private String gmtCreate;

    /**
    * 该账号最后修改时间
    */
    private String gmtModified;

    /**
    * 该账号token
    */
    private String token;

    private static final long serialVersionUID = 1L;
}