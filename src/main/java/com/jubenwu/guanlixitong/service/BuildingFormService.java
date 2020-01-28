package com.jubenwu.guanlixitong.service;

import com.jubenwu.guanlixitong.dto.AddBuildingFormDTO;
import com.jubenwu.guanlixitong.mapper.BuildingFormMapper;
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
        return buildingFormMapper.deleteByPrimaryKey(id);
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

    @Transactional
    public void insertBuildingFormDTO(AddBuildingFormDTO buildingFormDTO, List<Integer> childIds, Integer parentId) {
        String s = StringUtils.join(childIds, ",");
        BuildingForm buildingForm = new BuildingForm();
        buildingForm.setChildFormId(s);
        buildingForm.setUserId(parentId);
        buildingForm.setGmtCreate(System.currentTimeMillis() + "");
        buildingForm.setGmtModified(buildingForm.getGmtCreate());
        buildingForm.setLocker(1);
        insert(buildingForm);
    }

    public List<BuildingForm> selectByUserId(Integer userId) {
        List<BuildingForm> buildingForms = buildingFormMapper.selectByUserId(userId);
        return buildingForms;
    }
}
