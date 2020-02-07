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
public class BuildingForm implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 每一条建筑信息的集合，用,来分割
     */
    private String childFormId;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 修改时间
     */
    private String gmtModified;

    /**
     * 乐观锁
     */
    private Integer locker;

    /**
     * 审核状态 0未审核 1审核
     */
    private Integer isSend;

    /**
     * 地址示例
     */
    private String address;

    /**
     * 姓名示例
     */
    private String name;

    private static final long serialVersionUID = 1L;
}