package com.chen.service.impl;

import com.chen.dao.CourseContentMapper;
import com.chen.domain.Course;
import com.chen.domain.CourseSection;
import com.chen.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;

    //根据课程id查询关联的章节和课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(Integer CourseId) {
        return courseContentMapper.findSectionAndLessonByCourseId(CourseId);
    }

    @Override
    public Course findCourseByCourseId(Integer courseId) {
        return courseContentMapper.findCourseByCourseId(courseId);
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(CourseSection courseSection) {
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(courseSection);
    }
}
