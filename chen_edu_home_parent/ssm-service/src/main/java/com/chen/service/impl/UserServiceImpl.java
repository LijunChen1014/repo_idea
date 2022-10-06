package com.chen.service.impl;

import com.chen.dao.UserMapper;
import com.chen.domain.*;
import com.chen.service.UserService;
import com.chen.utils.Md5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> users = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public void updateUserUpdate(Integer id, String status) {
        userMapper.updateUserStatus(id,status);
    }

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if (user1!=null&& Md5.verify(user.getPassword(),"chen",user1.getPassword())){
            //用户存在且密码正确
            return user1;
        }else {
            return null;
        }

    }

    @Override
    public List<Role> findUserRelationRoleById(Integer Id) {
        List<Role> userRelationRoleById = userMapper.findUserRelationRoleById(Id);
        return userRelationRoleById;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        //根据用户Id清空给中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());
        //重新建立关系
        for (Integer roleId: userVo.getRoleIdList()
             ) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedBy("system");

            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        //查询用户所拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);
        //只保存角色ID
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        //根据角色ID查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //查询封装父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        //获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);
        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }
}
