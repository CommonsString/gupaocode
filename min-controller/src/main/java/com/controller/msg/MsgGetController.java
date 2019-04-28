package com.controller.msg;


import com.module.system.entity.vo.ContentVo;
import com.module.system.service.MsgGetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("getInfo")
@Api(tags = {"数据无参数获取"})
public class MsgGetController {


    @Autowired
    private MsgGetService msgGetService;


    @PostMapping(value = "/getReadAndContentType")
    @ApiOperation("获取新闻类别和阅读权限")
    public ResponseEntity saveMsg() {
        List<Object> result = msgGetService.findByReadAndType();
        return new ResponseEntity(result, HttpStatus.OK);
    }









}
