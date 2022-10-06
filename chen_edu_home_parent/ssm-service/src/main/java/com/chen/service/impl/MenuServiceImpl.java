package com.chen.service.impl;

import com.chen.dao.MenuMapper;
import com.chen.domain.Menu;
import com.chen.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    @Override
    public Menu findMenuById(Integer id) {
       return menuMapper.findMenuById(id);

    }
}
