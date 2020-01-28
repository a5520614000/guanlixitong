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
public class ResultListDTO<T> {
    private Integer code;
    private String message;
    private List<T> data;

}
