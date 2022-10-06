package com.chen.dao;

import com.chen.domain.Resource;
import com.chen.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {
    /*
    资源分页及多条件查询
     */
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
