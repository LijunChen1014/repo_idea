package com.chen.dao;

import com.chen.domain.Course;
import com.chen.domain.CourseVO;
import com.chen.domain.Teacher;

import java.util.List;

public interface CourseMapper {
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*
    新增课程信息
     */
    public void saveCourse(Course course);

    /*
    新增讲师信息
     */
    public void saveTeacher(Teacher teacher);

    /*
    回显课程信息
     */
    public CourseVO findCourseByID(Integer id);

    /*
    更新课程信息
     */
    public void updateCourse(Course course);
    /*
    更新讲师信息
     */
    public void updateTeacher(Teacher teacher);

    /*
    课程状态管理
     */
    public void updateCourseStatus(Course course);
}
