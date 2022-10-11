package com.chen.controller;


import com.chen.domain.Menu;
import com.chen.domain.MenuVo;
import com.chen.domain.Resource;
import com.chen.domain.ResponseResult;
import com.chen.service.MenuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /*
    查询所有菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(MenuVo menuVo){

        PageInfo<Menu> allMenu = menuService.findAllMenu(menuVo);
        return new ResponseResult(true,200,"查询所有菜单信息成功",allMenu);
    }

    /*
    回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        //判断根据id值判断当前是更新还是添加操作 判断id值是否为-1
        if (id == -1){
            //添加操作 回显信息中不需要查询menu信息
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            //封装数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);

            return new ResponseResult(true,200,"添加回显成功",map);
        }else {
            //修改操作 修改所有menu信息
            Menu menu = menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

            //封装数据
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);
            return new ResponseResult(true,200,"修改回显成功",map);
        }

    }

    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){

        if (menu.getId()!=null){
            menuService.updateMenu(menu);
            return  new ResponseResult(true,200,"更新菜单信息成功！",null);
        }else {
            menuService.saveMenu(menu);
            return new ResponseResult(true,200,"新增资源信息成功！",null);
        }

    }
    @RequestMapping("/deleteMenu")
    public ResponseResult deleteMenu(Integer id){
        menuService.deleteMenu(id);
        return new ResponseResult(true,200,"删除资源信息成功！",null);
    }
}
