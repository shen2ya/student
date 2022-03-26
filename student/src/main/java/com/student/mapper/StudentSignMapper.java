package com.student.mapper;

import com.student.entity.StudentSign;
import com.student.entity.StudentSignExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StudentSignMapper {
    int countByExample(StudentSignExample example);

    int deleteByExample(StudentSignExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentSign record);

    int insertSelective(StudentSign record);

    List<StudentSign> selectByExample(StudentSignExample example);

    StudentSign selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentSign record, @Param("example") StudentSignExample example);

    int updateByExample(@Param("record") StudentSign record, @Param("example") StudentSignExample example);

    int updateByPrimaryKeySelective(StudentSign record);

    int updateByPrimaryKey(StudentSign record);

    List<Map<String, Object>> getList(Map<String, Object> param);
}