package com.student.mapper;

import com.student.entity.StudentBuild;
import com.student.entity.StudentBuildExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentBuildMapper {
    int countByExample(StudentBuildExample example);

    int deleteByExample(StudentBuildExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentBuild record);

    int insertSelective(StudentBuild record);

    List<StudentBuild> selectByExample(StudentBuildExample example);

    StudentBuild selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentBuild record, @Param("example") StudentBuildExample example);

    int updateByExample(@Param("record") StudentBuild record, @Param("example") StudentBuildExample example);

    int updateByPrimaryKeySelective(StudentBuild record);

    int updateByPrimaryKey(StudentBuild record);
}