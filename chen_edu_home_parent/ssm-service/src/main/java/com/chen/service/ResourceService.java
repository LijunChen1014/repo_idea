package com.chen.service;

import com.chen.domain.Resource;
import com.chen.domain.ResourceVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ResourceService {
    /*
   资源分页及多条件查询
    */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
