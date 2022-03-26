package com.student.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.student.entity.StudentSign;
import com.student.entity.StudentSignExample;
import com.student.mapper.StudentSignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SignService {
    @Autowired
    private StudentSignMapper recordMapper;

    /**
     * 查询
     */
    public Map<String,Object> getList(StudentSign record) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            PageHelper.startPage(record.getIndex(), 20);

            StudentSignExample example = new StudentSignExample();
            List list = recordMapper.selectByExample(example);
            PageInfo pageData = new PageInfo(list);
            result.put("page", pageData);
            return result;
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Something Error");
            return result;
        }
    }
    /**
     * 修改
     */
    public Map<String,Object> update(StudentSign record) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            recordMapper.updateByPrimaryKeySelective(record);
            return result;
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Something Error");
            return result;
        }
    }
    /**
     * 添加
     */
    public Map<String,Object> add(StudentSign record) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            recordMapper.insertSelective(record);
            return result;
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Something Error");
            return result;
        }
    }

}
