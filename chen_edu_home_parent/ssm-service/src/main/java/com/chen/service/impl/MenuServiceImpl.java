package com.chen.service.impl;

import com.chen.dao.MenuMapper;
import com.chen.domain.Menu;
import com.chen.domain.MenuVo;
import com.chen.domain.PromotionAd;
import com.chen.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuList = menuMapper.findSubMenuListByPid(pid);
        return subMenuList;
    }

    @Override
    public PageInfo<Menu> findAllMenu(MenuVo menuVo) {

        PageHelper.startPage(menuVo.getCurrentPage(),menuVo.getPageSize());
        List<Menu> allMenu = menuMapper.findAllMenu();
        return new PageInfo<>(allMenu);

    }

    @Override
    public Menu findMenuById(Integer id) {
       return menuMapper.findMenuById(id);

    }

    @Override
    public void saveMenu(Menu menu) {

        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        Date date = new Date();
        menu.setUpdatedBy("system");
        menu.setUpdatedTime(date);
        menuMapper.updateMenu(menu);
    }

    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteMenu(id);
    }
}
