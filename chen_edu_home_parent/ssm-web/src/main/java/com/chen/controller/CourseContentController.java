package com.chen.controller;

import com.chen.domain.Course;
import com.chen.domain.CourseSection;
import com.chen.domain.ResponseResult;
import com.chen.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        List<CourseSection> sectionAndLesson = courseContentService.findSectionAndLessonByCourseId(courseId);

        return new ResponseResult(true,200,"查询章节及课程成功",sectionAndLesson);

    }

    //回显章节对应的课程信息
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);

        return new ResponseResult(true,200,"查询课程信息成功",course);

    }

    //新增以及更新章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if (courseSection.getId()== null){
            //新增操作
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true,200,"新增章节信息成功",null);
        }else {
            //更新操作
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true,200,"更新章节信息成功",null);
        }

    }

    //修改课程状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,Integer status){
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseContentService.updateSectionStatus(courseSection);

        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"章节状修改成功",map);          


    }
}
