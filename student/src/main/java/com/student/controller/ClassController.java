package com.student.controller;


import com.student.entity.StudentClass;
import com.student.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService service;

    @RequestMapping("/add")
    public Map<String,Object> add(@RequestBody StudentClass record) {
        return service.add(record);
    }

    @RequestMapping("/update")
    public Map<String,Object> update(@RequestBody StudentClass record) {
        return service.update(record);
    }

    @RequestMapping("/getList")
    public Map<String,Object> getList(@RequestBody StudentClass record) {
        return service.getList(record);
    }
}
