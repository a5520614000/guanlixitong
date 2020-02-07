package com.jubenwu.guanlixitong.service;

import com.jubenwu.guanlixitong.dto.AddBuildingFormDTO;
import com.jubenwu.guanlixitong.dto.UpdateBuildingFormDTO;
import com.jubenwu.guanlixitong.mapper.BuildingFormMapper;
import com.jubenwu.guanlixitong.model.Building;
import com.jubenwu.guanlixitong.model.BuildingForm;
import org.apache.commons.lang3.StringUtils;
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
public class BuildingFormService {

    @Resource
    private BuildingFormMapper buildingFormMapper;


    public int deleteByPrimaryKey(Integer id) {
        int i = buildingFormMapper.updateUserIdById(-2, id);
        return i;
    }


    public int insert(BuildingForm record) {
        return buildingFormMapper.insert(record);
    }


    public int insertSelective(BuildingForm record) {
        return buildingFormMapper.insertSelective(record);
    }


    public BuildingForm selectByPrimaryKey(Integer id) {
        return buildingFormMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(BuildingForm record) {
        return buildingFormMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(BuildingForm record) {
        return buildingFormMapper.updateByPrimaryKey(record);
    }

    /**
     * 插入主表
     *
     * @param buildingFormDTO DTO
     * @param childIds        包含的子表ID
     * @param userId          报送者的用户ID
     * @return 主表插入后的主键ID
     */
    @Transactional
    public Integer insertBuildingFormDTO(AddBuildingFormDTO buildingFormDTO, List<Integer> childIds, Integer userId) {
        //把子表LIST转换成字符串
        String s = StringUtils.join(childIds, ",");
        BuildingForm buildingForm = new BuildingForm();
        buildingForm.setChildFormId(s);
        buildingForm.setUserId(userId);
        Building building = buildingFormDTO.getBuilding().get(0);
        buildingForm.setAddress(building.getBuildingAddress());
        buildingForm.setIsSend(0);
        buildingForm.setName(building.getName());
        buildingForm.setGmtCreate(System.currentTimeMillis() + "");
        buildingForm.setGmtModified(buildingForm.getGmtCreate());
        buildingForm.setLocker(1);
        insert(buildingForm);
        return buildingForm.getId();
    }

    public List<BuildingForm> selectByUserId(Integer userId) {
        List<BuildingForm> buildingForms = buildingFormMapper.selectByUserId(userId);
        return buildingForms;
    }

    /**
     * 更新主表
     * @param buildingFormDTO DTO
     * @param parentId 主表ID
     * @return 1代表更新成功 0代表失败
     */
    @Transactional
    public void updateBuildingFormDTO(UpdateBuildingFormDTO buildingFormDTO, Integer parentId) {
        //通过主表ID找到“主表”
        BuildingForm buildingForm = buildingFormMapper.selectByPrimaryKey(parentId);
        //乐观锁
        Integer oldLocker = buildingFormMapper.selectLockerById(parentId);
        //乐观锁防高并发
        if (oldLocker.equals(buildingFormDTO.getLocker())) {
            buildingForm.setLocker(buildingForm.getLocker() + 1);
            buildingForm.setGmtModified(System.currentTimeMillis() + "");
            //重置为未审核状态
            buildingForm.setIsSend(0);
            Building myBuilding = buildingFormDTO.getBuilding().get(0);
            buildingForm.setName(myBuilding.getName());
            buildingForm.setAddress(myBuilding.getBuildingAddress());
            buildingFormMapper.updateByPrimaryKey(buildingForm);
        } else {
            // TODO: 2020-02-06 异常处理
            throw new RuntimeException();
        }
    }

    public Integer selectUserIdById(Integer parentId) {
        return buildingFormMapper.selectUserIdById(parentId);
    }


    public Integer selectLockerById(Integer parentId) {
        return buildingFormMapper.selectLockerById(parentId);
    }
}




