package com.student.mapper;

import com.student.entity.StudentClass;
import com.student.entity.StudentClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentClassMapper {
    int countByExample(StudentClassExample example);

    int deleteByExample(StudentClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentClass record);

    int insertSelective(StudentClass record);

    List<StudentClass> selectByExample(StudentClassExample example);

    StudentClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentClass record, @Param("example") StudentClassExample example);

    int updateByExample(@Param("record") StudentClass record, @Param("example") StudentClassExample example);

    int updateByPrimaryKeySelective(StudentClass record);

    int updateByPrimaryKey(StudentClass record);
}