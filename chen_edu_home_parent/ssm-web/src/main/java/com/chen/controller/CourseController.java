package com.chen.controller;

import com.chen.domain.Course;
import com.chen.domain.CourseVO;
import com.chen.domain.ResponseResult;
import com.chen.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/findCourse")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){

        List<Course> courseList = courseService.findCourseByCondition(courseVO);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseList);
        return result;
    }

    @RequestMapping("/courseUpload")
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
    /*
      新增课程信息及讲师信息
      新增课程信息和修改课程信息要写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        System.out.println(courseVO);
        if (courseVO.getId()==null || courseVO.getId()==0){
            //如果courseId为空 则说明是新增操作
            courseService.saveCourseOrTeacher(courseVO);
            return new ResponseResult(true, 200, "新增成功", null);
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }




    }
    /*
    根据ID查询具体的课程信息及关联的讲师信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        System.out.println(id);
        CourseVO courseVO = courseService.findCourseByID(id);
        return new ResponseResult(true, 200, "findCourseById success!", courseVO);

    }

    /*
    课程状态管理
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        courseService.updateCourseStatus(id, status);

        //响应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"状态更新成功",map);
    }



}
