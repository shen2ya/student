package com.student.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.student.entity.User;
import com.student.entity.UserBuild;
import com.student.entity.UserBuildExample;
import com.student.entity.UserClass;
import com.student.entity.UserClassExample;
import com.student.entity.UserExample;
import com.student.mapper.UserBuildMapper;
import com.student.mapper.UserClassMapper;
import com.student.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserClassMapper userClassMapper;
    @Autowired
    private UserBuildMapper userBuildMapper;

    @RequestMapping("/getUserBuild")
    public Map<String,Object> getUserBuild(@RequestBody User user) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            UserBuildExample example = new UserBuildExample();
            example.createCriteria().andUserIdEqualTo(user.getId());
            result.put("list",userBuildMapper.selectByExample(example));
            return result;
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Something Error");
            return result;
        }
    }

    @RequestMapping("/getUserClass")
    public Map<String,Object> getUserClass(@RequestBody User user) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            UserClassExample example = new UserClassExample();
            example.createCriteria().andUserIdEqualTo(user.getId());
            result.put("list",userClassMapper.selectByExample(example));
            return result;
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Something Error");
            return result;
        }
    }

    @RequestMapping("/updateUserBuild")
    public Map<String,Object> updateUserBuild(@RequestBody User user) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            UserBuildExample example = new UserBuildExample();
            example.createCriteria().andUserIdEqualTo(user.getId());
            userBuildMapper.deleteByExample(example);
            if(user.getBuildId() != null){
                for (Integer item:user.getBuildId()) {
                    UserBuild record = new UserBuild();
                    record.setUserId(user.getId());
                    record.setBuildId(item);
                    userBuildMapper.insert(record);
                }
            }
            return result;
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Something Error");
            return result;
        }
    }

    @RequestMapping("/updateUserClass")
    public Map<String,Object> updateUserClass(@RequestBody User user) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            UserClassExample example = new UserClassExample();
            example.createCriteria().andUserIdEqualTo(user.getId());
            userClassMapper.deleteByExample(example);
            if(user.getClassId() != null){
                for (Integer item:user.getClassId()) {
                    UserClass record = new UserClass();
                    record.setUserId(user.getId());
                    record.setClassId(item);
                    userClassMapper.insert(record);
                }
            }
            return result;
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Something Error");
            return result;
        }
    }


    /**
     * 查询
     */
    public Map<String,Object> getList(User user) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            PageHelper.startPage(user.getIndex(), 20);

            UserExample example = new UserExample();
            example.createCriteria().andNameLike('%' + user.getName() + '%');
            List<User> userList = userMapper.selectByExample(example);

            PageInfo pageData = new PageInfo(userList);
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
    public Map<String,Object> update(User user) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            userMapper.updateByPrimaryKeySelective(user);
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
    public Map<String,Object> register(User user) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            UserExample example = new UserExample();
            example.createCriteria().andPhoneEqualTo(user.getPhone());
            List<User> userList = userMapper.selectByExample(example);
            if(userList != null && userList.size() > 0){
                result.put("success", false);
                result.put("message", "电话已存在");
            }else{
                userMapper.insertSelective(user);
            }
            return result;
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Something Error");
            return result;
        }
    }
    /**
     * 登录
     */
    public Map<String,Object> login(User user) {
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        try{
            UserExample userExample = new UserExample();
            userExample.createCriteria().andNameEqualTo(user.getName()).andPwdEqualTo(user.getPwd()).andValidEqualTo(1);
            List<User> userList = userMapper.selectByExample(userExample);
            if(userList != null && userList.size() > 0){
                User loginUser = userList.get(0);
                result.put("user", loginUser);
            }else{
                result.put("success", false);
                result.put("message", "Username Or Password Error");
            }
            return result;
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Something Error");
            return result;
        }
    }

}
