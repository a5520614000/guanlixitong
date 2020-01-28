package com.jubenwu.guanlixitong.mapper;
import com.jubenwu.guanlixitong.model.BuildingForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Mapper
public interface BuildingFormMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuildingForm record);

    int insertSelective(BuildingForm record);

    BuildingForm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuildingForm record);

    int updateByPrimaryKey(BuildingForm record);

    List<BuildingForm> selectByUserId(@Param("userId")Integer userId);

    Integer countByUserId(@Param("userId")Integer userId);




}