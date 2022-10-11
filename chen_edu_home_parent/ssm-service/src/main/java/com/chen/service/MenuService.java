package com.chen.service;

import com.chen.domain.Menu;
import com.chen.domain.MenuVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MenuService {
    /*
   查询所有父子菜单信息
    */
    public List<Menu> findSubMenuListByPid(int pid);

    /*
   查询所有菜单列表
    */
    public PageInfo<Menu> findAllMenu(MenuVo menuVo);

    public Menu findMenuById(Integer id);
    public void saveMenu(Menu menu);
    public void updateMenu(Menu menu);
    public void deleteMenu(Integer id);
}
