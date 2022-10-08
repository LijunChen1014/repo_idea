package com.chen.service.impl;

import com.chen.dao.RoleMapper;
import com.chen.domain.*;
import com.chen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> id = roleMapper.findMenuByRoleId(roleId);
        return id;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //1 清空中间表
           roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2 分配菜单
        for (Integer mid: roleMenuVo.getMenuIdList()) {
            Role_menu_relation roleMenuRelation = new Role_menu_relation();
            roleMenuRelation.setMenuId(mid);
            roleMenuRelation.setRoleId(roleMenuVo.getRoleId());
            Date date = new Date();
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setUpdatedTime(date);

            roleMenuRelation.setUpdatedBy("system");
            roleMenuRelation.setCreatedBy("system");

            roleMapper.roleContextMenu(roleMenuRelation);
        }

    }

    @Override
    public void deleteRole(Integer RoleId) {
        //根据roleId 清空中间表
        roleMapper.deleteRoleContextMenu(RoleId);
        roleMapper.deleteRole(RoleId);
    }

    @Override
    public ResponseResult findResourceListByRoleId(Integer RoleId) {
        List<ResourceCategory> resourceCategoryList = roleMapper.findResourceCategoryByRoleId(RoleId);
        List<Resource> resourceList = roleMapper.findResourceByRoleId(RoleId);

//
//        for (ResourceCategory resourceCategory : resourceCategoryList) {
//            ArrayList<Resource> list = new ArrayList<>();
//            for (Resource resource : resourceList) {
//                if (resourceCategory.getId().equals(resource.getCategoryId())) {
//                    list.add(resource);
//                }
//
//            }
//            resourceCategory.setResourceList(list);
//
//        }

        for (ResourceCategory resourceCategory : resourceCategoryList) {

            resourceCategory.setResourceList(resourceList);

        }

        return new ResponseResult(true,200,"查询角色拥有的资源成功",resourceCategoryList);
    }

    @Override
    public void updateRoleContextResource(RoleResourceVO roleResourceVO) {

        roleMapper.deleteRoleContextMenu(roleResourceVO.getRoleId());

        for (Integer integer : roleResourceVO.getResourceIdList()) {
            Role_resource_relation role_resource_relation = new Role_resource_relation();
            role_resource_relation.setRoleId(roleResourceVO.getRoleId());
            role_resource_relation.setResourceId(integer);
            Date date = new Date();
            role_resource_relation.setCreatedTime(date);
            role_resource_relation.setUpdatedTime(date);
            role_resource_relation.setUpdatedBy("system");
            role_resource_relation.setCreatedBy("system");
            roleMapper.updateRoleContextResource(role_resource_relation);
        }

    }
}
