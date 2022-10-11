package com.chen.dao;

import com.chen.domain.*;

import java.util.List;

public interface RoleMapper {
    /*
    查询角色
     */
    public List<Role>  findAllRole(Role role);

    /*
    根据角色ID 查询该角色关联的菜单信息 ID
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
    根据RoleId清空中角色与菜单中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer RoleId);
    /*
    为角色分配菜单信息
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);
    /*
    删除角色
     */
    public void deleteRole(Integer roleId);
   /*
   查询当前角色所拥有的资源信息
    */
    public List<Resource> findResourceByRoleId(Integer id);
    /*
    查询当前角色所拥有的资源分类信息
     */
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer id);

    /*
    根据roleId删除角色与资源的关联关系
     */
    public void deleteRoleContextResource(Integer roleId);
    /*
    为角色分配资源
     */
    public void updateRoleContextResource(Role_resource_relation role_resource_relation);

    /*
    新增角色信息
     */
    public  void saveRole(Role role);

    public void updateRole(Role role);
}
