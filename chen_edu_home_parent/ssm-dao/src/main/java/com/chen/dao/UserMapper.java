package com.chen.dao;

import com.chen.domain.*;
import org.apache.ibatis.annotations.Param;

import javax.swing.plaf.PanelUI;
import java.util.List;

public interface UserMapper {
    /*
    用户分页 多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);
    /*
    更新用户状态
     */
    public void updateUserStatus(@Param("id") Integer id ,@Param("status") String status);

    /*
    用户登录（根据用户名查询具体的用户信息）
     */
    public User login(User user);


    /*
    根据用户Id清空中间表
     */
    public void deleteUserContextRole(Integer userId);
    /*
    分配角色
     */
    public void userContextRole(User_Role_relation user_role_relation);

    //动态登录
    /*
   根据用户Id 查询用户关联的角色信息
    */
    public List<Role> findUserRelationRoleById(Integer Id);
    /*
    根据角色ID 查询角色所拥有的顶级菜单
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    /*
    根据Pid查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(Integer pid);
    /*
    获取用户拥有的资源权限信息（实质上是角色与资源的关系）
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);



}
