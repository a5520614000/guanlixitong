package com.jubenwu.guanlixitong.service;

import com.jubenwu.guanlixitong.dto.AddBuildingFormDTO;
import com.jubenwu.guanlixitong.mapper.BuildingMapper;
import com.jubenwu.guanlixitong.model.Building;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
@Service
public class BuildingService {

    @Resource
    private BuildingMapper buildingMapper;


    public int deleteByPrimaryKey(Integer id) {
        return buildingMapper.deleteByPrimaryKey(id);
    }


    public int insert(Building record) {
        return buildingMapper.insert(record);
    }


    public int insertSelective(Building record) {
        return buildingMapper.insertSelective(record);
    }


    public Building selectByPrimaryKey(Integer id) {
        return buildingMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Building record) {
        return buildingMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Building record) {
        return buildingMapper.updateByPrimaryKey(record);
    }

    @Transactional
    public List<Integer> insertBuildingList(AddBuildingFormDTO buildingFormDTO, Integer parentId) {
        List<Integer> childIds = new ArrayList<>();
        List<Building> buildingList = buildingFormDTO.getBuilding();
        for (Building building : buildingList) {
            building.setParentId(parentId);
            building.setLocker(1);
            building.setGmtCreate(System.currentTimeMillis()+"");
            building.setGmtModified(building.getGmtCreate());
            buildingMapper.insertAndGetId(building);
            childIds.add(building.getId());
        }
        return childIds;
    }
}

