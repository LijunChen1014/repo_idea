package com.chen.service.impl;

import com.chen.dao.RoleMapper;
import com.chen.domain.*;
import com.chen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<ResourceCategory> resourceCategoryByRoleId = roleMapper.findResourceCategoryByRoleId(RoleId);
        List<Resource> resourceByRoleId = roleMapper.findResourceByRoleId(RoleId);

        ArrayList<Resource> list = new ArrayList<>();
        for (int i = 0; i < resourceCategoryByRoleId.size(); i++) {
            for (Resource resource : resourceByRoleId) {
                if (resourceCategoryByRoleId.get(i).getId().equals(resource.getCategoryId())){
                    list.add(resource);
                }

            }
            resourceCategoryByRoleId.get(i).setResourceList(list);
            list.clear();
        }



        return new ResponseResult(true,200,"查询角色拥有的资源成功",resourceCategoryByRoleId);
    }
}
