package com.student.controller;


import com.student.entity.StudentBuild;
import com.student.entity.StudentClass;
import com.student.service.BuildService;
import com.student.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/build")
public class BuildController {

    @Autowired
    BuildService service;

    @RequestMapping("/add")
    public Map<String,Object> add(@RequestBody StudentBuild record) {
        return service.add(record);
    }

    @RequestMapping("/update")
    public Map<String,Object> update(@RequestBody StudentBuild record) {
        return service.update(record);
    }

    @RequestMapping("/getList")
    public Map<String,Object> getList(@RequestBody StudentBuild record) {
        return service.getList(record);
    }
}
