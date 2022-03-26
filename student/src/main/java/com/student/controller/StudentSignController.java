package com.student.controller;


import com.student.entity.Student;
import com.student.entity.StudentSign;
import com.student.service.StudentService;
import com.student.service.StudentSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/studentSign")
public class StudentSignController {

    @Autowired
    StudentSignService service;

    @RequestMapping("/add")
    public Map<String,Object> add(@RequestBody StudentSign record) {
        return service.add(record);
    }

    @RequestMapping("/update")
    public Map<String,Object> update(@RequestBody StudentSign record) {
        return service.update(record);
    }

    @RequestMapping("/getList")
    public Map<String,Object> getList(@RequestBody Map<String, Object> record) {
        return service.getList(record);
    }
}
