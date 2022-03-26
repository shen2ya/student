package com.student.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.student.entity.StudentClass;
import com.student.entity.StudentClassExample;
import com.student.mapper.StudentClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ClassService {
    @Autowired
    private StudentClassMapper recordMapper;

    /**
     * 查询
     */
    public Map<String,Object> getList(StudentClass record) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            if(record.getIndex() == null){
                StudentClassExample example = new StudentClassExample();
                example.createCriteria().andValidEqualTo(1);
                List list = recordMapper.selectByExample(example);
                result.put("page", list);
                return result;
            }
            PageHelper.startPage(record.getIndex(), 20);
            StudentClassExample example = new StudentClassExample();
            example.createCriteria().andNameLike('%' + record.getName() + '%');
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
    public Map<String,Object> update(StudentClass record) {
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
    public Map<String,Object> add(StudentClass record) {
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
