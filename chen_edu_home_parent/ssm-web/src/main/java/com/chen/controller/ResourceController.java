package com.chen.controller;

import com.chen.domain.Resource;
import com.chen.domain.ResourceVo;
import com.chen.domain.ResponseResult;
import com.chen.service.ResourceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResourceByPage(@RequestBody ResourceVo resourceVo){

        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVo);

        return new ResponseResult(true,200,"资源信息分页多条件查询成功",pageInfo);
    }
    //saveOrUpdateResource

    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if (resource.getId()!=null){
            resourceService.updateResource(resource);
            return  new ResponseResult(true,200,"更新资源信息成功！",null);
        }else {
            resourceService.saveResource(resource);
            return new ResponseResult(true,200,"新增资源信息成功！",null);
        }

    }
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){
        resourceService.deleteResource(id);
        return  new ResponseResult(true,200,"删除资源信息成功！",null);
    }

}
