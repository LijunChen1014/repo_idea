package com.chen.controller;


import com.chen.domain.PromotionAd;
import com.chen.domain.PromotionAdVo;
import com.chen.domain.ResponseResult;
import com.chen.service.PromotionAdService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
    广告分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo){

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);
        return new ResponseResult(true,200,"广告分页查询成功",pageInfo);
    }

    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()){
            //文件为空
            throw new RuntimeException();
        }

        //获取项目部署路径
        //E:\Professional\apache-tomcat-8.5.81\webapps\ssm-web
        String realPath = request.getServletContext().getRealPath("/");
        //E:\Professional\apache-tomcat-8.5.81\webapps\
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        //获取源文件名
        String originalFilename = file.getOriginalFilename();

        //生成新文件名
        String newFileName =  System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //文件上传
        String uploadPath = substring+"upload\\";

        File filePath = new File(uploadPath, newFileName);
        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录："+filePath);
        }

        //图片上传
        file.transferTo(filePath);

        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult uploadSuccess = new ResponseResult(true, 200, "uploadSuccess", map);
        return uploadSuccess;

    }


    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult  updatePromotionAdStatus(Integer id,Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);
        return new ResponseResult(true, 200, "广告状态修改成功", null);
    }


    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        if (promotionAd.getId()!=null){
            //更新操作
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"修改广告信息成功",null);
        }
        else {
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"添加广告成功",null);
        }

    }

}
