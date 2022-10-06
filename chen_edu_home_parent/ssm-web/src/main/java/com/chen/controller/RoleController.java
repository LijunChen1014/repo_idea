package com.chen.controller;

import com.chen.domain.Menu;
import com.chen.domain.ResponseResult;
import com.chen.domain.Role;
import com.chen.domain.RoleMenuVo;
import com.chen.service.MenuService;
import com.chen.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true,200,"查询角色信息成功",allRole);
    }

    @Autowired
      private MenuService menuService;

    //查询所有的父子菜单信息
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        //-1 表示查询所有的父级菜单
        List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("parentMenuList",subMenuListByPid);
        return new ResponseResult(true,200,"查询所有的父子菜单信息成功",hashMap);
    }
    /*
   根据角色ID 查询该角色关联的菜单信息 ID
    */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true,200,"查询角色关联的菜单信息成功",menuByRoleId);
    }

    /*
    为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        System.out.println(roleMenuVo);
        roleService.roleContextMenu(roleMenuVo);
        return new ResponseResult(true,200,"为角色分配菜单成功",null);
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole( Integer id){

        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除角色成功",null);

    }
}
