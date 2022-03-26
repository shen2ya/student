package com.student.mapper;

import com.student.entity.UserClass;
import com.student.entity.UserClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserClassMapper {
    int countByExample(UserClassExample example);

    int deleteByExample(UserClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserClass record);

    int insertSelective(UserClass record);

    List<UserClass> selectByExample(UserClassExample example);

    UserClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserClass record, @Param("example") UserClassExample example);

    int updateByExample(@Param("record") UserClass record, @Param("example") UserClassExample example);

    int updateByPrimaryKeySelective(UserClass record);

    int updateByPrimaryKey(UserClass record);
}