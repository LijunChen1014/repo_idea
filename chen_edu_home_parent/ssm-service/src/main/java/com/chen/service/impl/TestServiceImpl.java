package com.chen.service.impl;

import com.chen.dao.TestMapper;
import com.chen.domain.Test;
import com.chen.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> findAllTest() {
        List<Test> testList = testMapper.findAllTest();
        return testList;
    }
}
