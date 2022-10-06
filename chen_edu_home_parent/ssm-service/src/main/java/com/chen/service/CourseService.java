package com.chen.service;

import com.chen.domain.Course;
import com.chen.domain.CourseVO;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*
   新增课程信息
    */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
   回显课程信息
    */
    public CourseVO findCourseByID(Integer id);

    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    public void updateCourseStatus(Integer courseId,Integer status);


}
