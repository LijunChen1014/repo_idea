package com.chen.service;

import com.chen.domain.ResponseResult;
import com.chen.domain.Role;
import com.chen.domain.RoleMenuVo;
import com.chen.domain.RoleResourceVO;

import java.util.List;

public interface RoleService {
    /*
   查询角色
    */
    public List<Role> findAllRole(Role role);
    /*
   根据角色ID 查询该角色关联的菜单信息 ID
    */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
    为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);
    /*
    删除角色
     */
    public void deleteRole(Integer RoleId);

    /*
    获取当前角色拥有的资源信息
     */
    public ResponseResult findResourceListByRoleId(Integer RoleId);

    /*
    为角色分配资源
     */
    public void updateRoleContextResource(RoleResourceVO roleResourceVO);

    public  void saveRole(Role role);

    public void updateRole(Role role);
}
