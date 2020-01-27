package com.jubenwu.guanlixitong.dto;

import lombok.Data;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;
}
