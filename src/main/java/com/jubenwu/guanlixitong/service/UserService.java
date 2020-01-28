package com.jubenwu.guanlixitong.service;

import com.jubenwu.guanlixitong.dto.LoginRequestDTO;
import com.jubenwu.guanlixitong.etc.MyToken;
import com.jubenwu.guanlixitong.mapper.UserMapper;
import com.jubenwu.guanlixitong.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }


    public int insert(User record) {
        return userMapper.insert(record);
    }


    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }


    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * 账号登录
     *
     * @param loginRequestDTO
     * @return 账号不存在返回-1 密码错误返回0 正确返回token
     */
    @Transactional
    public String login(LoginRequestDTO loginRequestDTO) {
        List<User> users = userMapper.selectByAccountId(loginRequestDTO.getAccountId());

        if (users == null | users.size() == 0) {
            return "-1";
        }
        User user = users.get(0);
        if (user.getPassword().equals(loginRequestDTO.getPassword())) {
            String token = MyToken.getToken(loginRequestDTO.getAccountId());
            userMapper.updateTokenAndGmtModifiedById(token, System.currentTimeMillis() + "", user.getId());
            return token;
        } else {
            return "0";
        }
    }

    @Transactional
    public String registerUser(User user) {
        Integer integer = userMapper.countByAccountId(user.getAccountId());
        if (integer==0){
            user.setGmtCreate(System.currentTimeMillis()+"");
            user.setGmtModified(user.getGmtCreate());
            String token = MyToken.getToken(user.getAccountId());
            user.setToken(token);
            user.setLevel(1);
            user.setLevelName("工作人员");
            userMapper.insert(user);
            return token;
        }else {
            return "0";
        }

    }

    /**
     * 用token找到userId
     * @param token
     * @return 存在返回userId，不存在返回0
     */
    public Integer selectIdByToken(String token){
        try {
            return userMapper.selectIdByToken(token).get(0);
        }catch (IndexOutOfBoundsException e){
            return 0;
        }
    }
}
