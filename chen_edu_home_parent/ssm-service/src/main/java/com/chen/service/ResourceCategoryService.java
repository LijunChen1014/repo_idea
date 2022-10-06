package com.chen.service;

import com.chen.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {
    public List<ResourceCategory> findAllResourceCategory();
    /*
   添加资源分类
    */
    public void saveResourceCategory(ResourceCategory resourceCategory);
    /*
   修改资源分类
    */
    public void updateResourceCategory(ResourceCategory resourceCategory);
    /*
   删除资源分类
   */
    public void deleteResourceCategory(Integer id);
}
