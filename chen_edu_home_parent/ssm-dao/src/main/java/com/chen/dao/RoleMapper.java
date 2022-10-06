package com.chen.dao;

import com.chen.domain.Role;
import com.chen.domain.Role_menu_relation;

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
}
