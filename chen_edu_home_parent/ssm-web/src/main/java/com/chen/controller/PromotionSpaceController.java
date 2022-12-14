package com.chen.controller;


import com.chen.domain.PromotionSpace;
import com.chen.domain.ResponseResult;
import com.chen.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {
    @Autowired
    private PromotionSpaceService promotionSpaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){

        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();

        return  new ResponseResult(true,200,"查询广告位成功",allPromotionSpace);
    }

    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if (promotionSpace.getId()!=null){
            //更新操作
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"更新广告位成功",null);
        }
        else {
            promotionSpaceService.savePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"添加广告位成功",null);
        }

    }

    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        return new ResponseResult(true,200,"查询广告位成功",promotionSpace);
    }



}
