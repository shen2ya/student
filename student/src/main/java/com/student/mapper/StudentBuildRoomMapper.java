package com.student.mapper;

import com.student.entity.StudentBuildRoom;
import com.student.entity.StudentBuildRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentBuildRoomMapper {
    int countByExample(StudentBuildRoomExample example);

    int deleteByExample(StudentBuildRoomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentBuildRoom record);

    int insertSelective(StudentBuildRoom record);

    List<StudentBuildRoom> selectByExample(StudentBuildRoomExample example);

    StudentBuildRoom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentBuildRoom record, @Param("example") StudentBuildRoomExample example);

    int updateByExample(@Param("record") StudentBuildRoom record, @Param("example") StudentBuildRoomExample example);

    int updateByPrimaryKeySelective(StudentBuildRoom record);

    int updateByPrimaryKey(StudentBuildRoom record);
}