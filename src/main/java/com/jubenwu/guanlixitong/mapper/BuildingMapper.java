package com.jubenwu.guanlixitong.mapper;
import org.apache.ibatis.annotations.Param;

import com.jubenwu.guanlixitong.model.Building;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Mapper
public interface BuildingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Building record);

    int insertSelective(Building record);

    Building selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Building record);

    int updateByPrimaryKey(Building record);

    int insertAndGetId(Building building);

    Integer selectFirstLockerbyId(@Param("id")Integer id);


}