package com.chen.controller;


import com.chen.domain.ResourceCategory;
import com.chen.domain.ResponseResult;
import com.chen.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;


    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

        return  new ResponseResult(true,200,"查询所有资源分类成功",allResourceCategory);
    }

    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        if (resourceCategory.getId()!=null){
           resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"修改资源分类成功",null);
        }else {

            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"新增资源分类成功",null);
        }
    }
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){

        resourceCategoryService.deleteResourceCategory(id);
        return new ResponseResult(true,200,"删除资源分类成功",null);
    }

}
