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

    /**
     * 插入子表
     *
     * @param buildingFormDTO 数据交换模型
     * @param userId          用户ID
     * @return 子表插入后获得的主键ID
     */
    @Transactional
    public List<Integer> insertBuildingList(AddBuildingFormDTO buildingFormDTO, Integer userId) {
//        System.out.println("buildDTO:"+buildingFormDTO.toString());
        List<Integer> childIds = new ArrayList<>();
        //从DTO处获取子表LIST
        List<Building> buildingList = buildingFormDTO.getBuilding();
        System.out.println("list:"+buildingList);
        //迭代并插入每一个子表
        for (Building building : buildingList) {
            insetNewForm(childIds, building, userId);
        }
        return childIds;
    }

    @Transactional
    public List<Integer> updateByPrimaryKey(UpdateBuildingFormDTO buildingFormDTO, Integer parentId, Integer userId) {

        List<Integer> childIds = new ArrayList<>();
        List<Building> buildingList = buildingFormDTO.getBuilding();

        for (Building building : buildingList) {
            Integer id = building.getId();
            if (id == null) {
                id = 0;
            }
            System.out.println("id:" + id);
            //该问题自带ID，那么进入更新
            if (id > 0) {
                Integer userId1 = buildingMapper.selectUserIdById(building.getId());
                //判断该问题是否属于该用户
                if (userId1.equals(userId)) {
                    Integer oldLocker = buildingMapper.selectFirstLockerbyId(id);
                    //乐观锁
                    if (oldLocker < (building.getLocker() + 1)) {
                        building.setParentId(parentId);
                        building.setLocker((building.getLocker() + 1));
                        building.setGmtModified(System.currentTimeMillis() + "");
                        buildingMapper.updateByPrimaryKey(building);
                    }
                    childIds.add(id);
                }
            } else {
                //否则新增
                System.out.println("parentId:" + parentId);
                building.setParentId(parentId);
                building.setUserId(userId);
                building.setLocker(1);
                building.setGmtCreate(System.currentTimeMillis() + "");
                building.setGmtModified(building.getGmtCreate());
                buildingMapper.insertAndGetId(building);
                childIds.add(building.getId());
            }

        }
        return childIds;
    }

    //新增
    private void insetNewForm(List<Integer> childIds, Building building, Integer userId) {
        building.setUserId(userId);
        building.setLocker(1);
        building.setGmtCreate(System.currentTimeMillis() + "");
        building.setGmtModified(building.getGmtCreate());
        buildingMapper.insert(building);
//        buildingMapper.insertAndGetId(building);
        childIds.add(building.getId());
    }

    @Transactional
    public void updateParentIdById(Integer parentId, List<Integer> childIds) {
        for (Integer childId : childIds) {
            buildingMapper.updateParentIdById(parentId, childId);
        }
    }

    public Integer selectUserIdById(Integer id) {
        return buildingMapper.selectUserIdById(id);
    }
}






