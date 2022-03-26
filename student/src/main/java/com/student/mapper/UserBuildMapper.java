package com.student.mapper;

import com.student.entity.UserBuild;
import com.student.entity.UserBuildExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBuildMapper {
    int countByExample(UserBuildExample example);

    int deleteByExample(UserBuildExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBuild record);

    int insertSelective(UserBuild record);

    List<UserBuild> selectByExample(UserBuildExample example);

    UserBuild selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserBuild record, @Param("example") UserBuildExample example);

    int updateByExample(@Param("record") UserBuild record, @Param("example") UserBuildExample example);

    int updateByPrimaryKeySelective(UserBuild record);

    int updateByPrimaryKey(UserBuild record);
}