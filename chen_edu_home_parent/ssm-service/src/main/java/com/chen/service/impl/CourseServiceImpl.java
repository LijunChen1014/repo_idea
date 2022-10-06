package com.chen.service.impl;

import com.chen.dao.CourseContentMapper;
import com.chen.dao.CourseMapper;
import com.chen.domain.Course;
import com.chen.domain.CourseSection;
import com.chen.domain.CourseVO;
import com.chen.domain.Teacher;
import com.chen.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;


    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {

        List<Course> courseList = courseMapper.findCourseByCondition(courseVO);
        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);

        System.out.println(course);

        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        courseMapper.saveCourse(course);
        //获取新插入数据的ID
        int courseId = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();
        //BeanUtils.copyProperties();
        //对象属性有Date类型为null时，使用org.apache.commons.beanutils复制对象转换异常。
        BeanUtils.copyProperties(teacher,courseVO);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(courseId);
        System.out.println(teacher);
        courseMapper.saveTeacher(teacher);
    }  

    @Override
    public CourseVO findCourseByID(Integer id) {

        return courseMapper.findCourseByID(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);
        Date date = new Date();
        //补全信息
        course.setUpdateTime(date);
        courseMapper.updateCourse(course);


        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全信息
        teacher.setCourseId(course.getId());//非常重要
        teacher.setUpdateTime(date);
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(Integer courseId, Integer status) {

        //封装数据
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        courseMapper.updateCourseStatus(course);
    }




}
