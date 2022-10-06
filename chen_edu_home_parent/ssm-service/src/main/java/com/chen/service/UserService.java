package com.chen.service;

import com.chen.domain.ResponseResult;
import com.chen.domain.Role;
import com.chen.domain.User;
import com.chen.domain.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    /*
    用户 分页查询
     */

    public PageInfo findAllUserByPage(UserVo userVo);
    /*
    更新用户状态
     */
    public void updateUserUpdate(Integer id ,String status);
    /*
   用户登录（根据用户名查询具体的用户信息）
    */
    public User login(User user) throws Exception;

    /*
    根据用户Id 查询用户关联的角色信息
    */
    public List<Role> findUserRelationRoleById(Integer Id);

    /*
    用户关联角色
     */
    public void userContextRole(UserVo userVo);
    /*
    获取用户权限，进行菜单动态展示
     */
    public ResponseResult getUserPermissions(Integer userId);
}
