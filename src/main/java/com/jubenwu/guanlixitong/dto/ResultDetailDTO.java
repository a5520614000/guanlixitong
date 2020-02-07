package com.jubenwu.guanlixitong.dto;

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
public class ResultDetailDTO<T> {
    private Integer code;
    private Integer locker;
    private List<T> data;

}
