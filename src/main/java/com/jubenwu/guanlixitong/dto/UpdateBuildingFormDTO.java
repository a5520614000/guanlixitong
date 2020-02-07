package com.jubenwu.guanlixitong.dto;

import com.jubenwu.guanlixitong.model.Building;
import lombok.Data;

import java.util.List;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Data
public class UpdateBuildingFormDTO {
    /**
     * 上报人信息
     */
    private String token;
    /**
     * 建筑list
     */
    private List<Building> building;
    /**
     * locker
     */
    private Integer locker;
}
