package com.jubenwu.guanlixitong.service;

import com.jubenwu.guanlixitong.dto.AddBuildingFormDTO;
import com.jubenwu.guanlixitong.dto.UpdateBuildingFormDTO;
import com.jubenwu.guanlixitong.mapper.BuildingFormMapper;
import com.jubenwu.guanlixitong.mapper.BuildingMapper;
import com.jubenwu.guanlixitong.model.Building;
import com.jubenwu.guanlixitong.model.BuildingForm;
import org.apache.commons.lang3.StringUtils;
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

    @Resource
    private BuildingFormMapper buildingFormMapper;


    @Transactional
    public void deleteByPrimaryKey(Integer id) {
        Integer parentId = buildingMapper.selectParentIdById(id);
        String str = buildingFormMapper.selectChildFormIdById(parentId);
        String[] strings = str.split(",");
        String newStr = "";
        //设置该列第一个附表ID，方便查询
        String startId = "";
        for (String s : strings) {
            System.out.println("判断S是否为ID：" + s + ",id:" + id);
            //如果该ID不等于需要删除的ID
            if (!(s.equals(id + ""))) {
                newStr += s+",";
                if (StringUtils.isBlank(startId)) {
                    startId = s;
                }
            }
        }
        if (StringUtils.isBlank(newStr)) {
            //最后一条删除直接删除主表，所以有异常
            // TODO: 2020-02-06 异常处理
            throw new RuntimeException();
        }
        String removeStr = StringUtils.removeEnd(newStr, ",");
        buildingMapper.updateUserIdById(-2, id);
        BuildingForm buildingForm = buildingFormMapper.selectByPrimaryKey(parentId);
        Building building = buildingMapper.selectByPrimaryKey(Integer.parseInt(startId));

        buildingForm.setName(building.getName());
        buildingForm.setAddress(building.getBuildingAddress());
        buildingForm.setGmtModified(System.currentTimeMillis() + "");
        buildingForm.setIsSend(0);
        buildingForm.setChildFormId(removeStr);
        System.out.println("修改过后的主表："+buildingForm.toString());
        buildingFormMapper.updateByPrimaryKey(buildingForm);
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
        System.out.println("list:" + buildingList);
        //迭代并插入每一个子表
        for (Building building : buildingList) {
            insetNewForm(childIds, building, userId);
        }
        return childIds;
    }

    /**
     * 更新副表
     *
     * @param buildingFormDTO DTO
     * @param userId          用户ID
     * @return 主表ID
     */
    @Transactional
    public Integer updateByPrimaryKey(UpdateBuildingFormDTO buildingFormDTO, Integer userId) {

        Integer parentIdDTO = 0;
        List<Building> buildingList = buildingFormDTO.getBuilding();
        for (Building building : buildingList) {
            Integer id = building.getId();
            if (id == null) {
                // TODO: 2020-02-06 异常处理
                throw new RuntimeException();
            }
            System.out.println("BuildingService_id:" + id);
            //该问题自带ID，那么进入更新
            if (id > 0) {
                Integer userId1 = buildingMapper.selectUserIdById(building.getId());
                //判断该问题是否属于该用户
                if (userId1.equals(userId)) {
                    Integer parentId = buildingMapper.selectParentIdById(id);
                    Integer oldLocker = buildingMapper.selectFirstLockerbyId(id);
                    //乐观锁
                    if (oldLocker < (building.getLocker() + 1)) {
                        building.setParentId(parentId);
                        building.setLocker((building.getLocker() + 1));
                        building.setGmtModified(System.currentTimeMillis() + "");
                        buildingMapper.updateByPrimaryKey(building);
                    }
                    parentIdDTO = parentId;
                }
            } else {
                // TODO: 2020-02-06
                throw new RuntimeException();
            }

        }
        return parentIdDTO;
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






