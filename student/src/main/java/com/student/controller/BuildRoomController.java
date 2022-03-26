package com.student.controller;


import com.student.entity.StudentBuildRoom;
import com.student.service.BuildRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/buildRoom")
public class BuildRoomController {

    @Autowired
    BuildRoomService service;

    @RequestMapping("/add")
    public Map<String,Object> add(@RequestBody StudentBuildRoom record) {
        return service.add(record);
    }

    @RequestMapping("/update")
    public Map<String,Object> update(@RequestBody StudentBuildRoom record) {
        return service.update(record);
    }

    @RequestMapping("/getList")
    public Map<String,Object> getList(@RequestBody StudentBuildRoom record) {
        return service.getList(record);
    }
}
