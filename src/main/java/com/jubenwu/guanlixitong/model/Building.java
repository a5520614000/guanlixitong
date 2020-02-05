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
public class Building implements Serializable {
    /**
     * id 自动
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String cardId;

    /**
     * 建筑地址
     */
    private String buildingAddress;

    /**
     * 层数
     */
    private Integer floor;

    /**
     * 用途
     */
    private String use;

    /**
     * 备注
     */
    private String others;

    /**
     * 以下由后端处理
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 以下由后端处理
     * 最后修改时间
     */
    private String gmtModified;

    /**
     * 以下由后端处理
     * 所属表id
     */
    private Integer parentId;

    /**
     * 以下由后端处理
     * 乐观锁
     */
    private Integer locker;

    /**
     * 上报该表的用户ID
     */
    private Integer userId;

    /**
     * 总面积
     */
    private Double totalArea;

    /**
     * 砖混结构面积
     */
    private Double zhuanhun;

    /**
     * 砖木结构面积
     */
    private Double zhuanmu;

    /**
     * 其他结构面积
     */
    private Double other;

    private static final long serialVersionUID = 1L;
}