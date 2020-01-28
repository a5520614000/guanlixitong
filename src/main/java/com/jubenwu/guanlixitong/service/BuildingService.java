package com.jubenwu.guanlixitong.service;

import com.jubenwu.guanlixitong.dto.AddBuildingFormDTO;
import com.jubenwu.guanlixitong.dto.UpdateBuildingFormDTO;
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
            insetNewForm(parentId, childIds, building);
        }
        return childIds;
    }

    @Transactional
    public List<Integer> updateByPrimaryKey(UpdateBuildingFormDTO buildingFormDTO, Integer parentId) {

        List<Integer> childIds = new ArrayList<>();
        List<Building> buildingList = buildingFormDTO.getBuilding();
        for (Building building : buildingList) {
            //该问题自带ID，那么进入更新
            if (building.getId() > 0) {
                Integer oldLocker = buildingMapper.selectFirstLockerbyId(building.getId());
                //乐观锁
                if (oldLocker < (building.getLocker() + 1)) {
                    building.setParentId(parentId);
                    building.setLocker((building.getLocker() + 1));
                    building.setGmtModified(System.currentTimeMillis() + "");
                    childIds.add(building.getId());
                    buildingMapper.updateByPrimaryKey(building);
                }
            } else {
                //否则新增
                insetNewForm(parentId, childIds, building);
            }

        }
        return childIds;
    }

    private void insetNewForm(Integer parentId, List<Integer> childIds, Building building) {
        building.setParentId(parentId);
        building.setLocker(1);
        building.setGmtCreate(System.currentTimeMillis() + "");
        building.setGmtModified(building.getGmtCreate());
        buildingMapper.insertAndGetId(building);
        childIds.add(building.getId());
    }
}

