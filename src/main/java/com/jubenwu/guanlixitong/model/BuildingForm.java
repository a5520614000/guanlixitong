package com.jubenwu.guanlixitong.model;

import lombok.Data;

import java.io.Serializable;

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
     * id 自动
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

    /*以下由后端处理*/
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

    private static final long serialVersionUID = 1L;
}