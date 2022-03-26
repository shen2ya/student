package com.student.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.student.entity.Student;
import com.student.entity.StudentExample;
import com.student.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StudentService {
    @Autowired
    private StudentMapper recordMapper;

    /**
     * 查询
     */
    public Map<String,Object> getList(Map<String,Object> record) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            Integer index = Integer.parseInt(record.get("index").toString());
            PageHelper.startPage(index, 20);
            List list = recordMapper.getList(record);
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
    public Map<String,Object> update(Student record) {
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
    public Map<String,Object> add(Student record) {
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
