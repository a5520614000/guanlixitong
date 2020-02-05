package com.jubenwu.guanlixitong.mapper;

import com.jubenwu.guanlixitong.model.Building;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

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

    int updateParentIdById(@Param("updatedParentId") Integer updatedParentId, @Param("id") Integer id);

    Integer selectUserIdById(@Param("id") Integer id);

    int insertAndGetId(Building building);

    Integer selectFirstLockerbyId(@Param("id") Integer id);
}