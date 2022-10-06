package com.chen.dao;

import com.chen.domain.Course;
import com.chen.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    //根据课程id查询关联的章节和课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(Integer CourseId);

    //回显章节对应的课程信息
    public Course findCourseByCourseId(Integer courseId);

    //新建章节信息
    public void saveSection(CourseSection courseSection);

    public void updateSection(CourseSection courseSection);

    //修改章节状态
    public void updateSectionStatus(CourseSection courseSection);
}
