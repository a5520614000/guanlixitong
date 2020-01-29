package com.jubenwu.guanlixitong.mapper;
import com.jubenwu.guanlixitong.model.User;
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
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectByAccountId(@Param("accountId")String accountId);

    int updateTokenAndGmtModifiedById(@Param("updatedToken")String updatedToken,@Param("updatedGmtModified")String updatedGmtModified,@Param("id")Integer id);

    Integer countByAccountId(@Param("accountId")String accountId);

    List<Integer> selectIdByToken(@Param("token")String token);






}