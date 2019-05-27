package com.controller.msg;

import com.module.system.domain.ContentType;
import com.module.system.entity.vo.ContentVo;
import com.module.system.service.ContentTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type")
@Api(tags = {"新闻类型"})
public class ContentTypeController {

    @Autowired
    private ContentTypeService contentTypeService;

    @PostMapping(value = "/addType")
    @ApiOperation("新增类型")
    public ResponseEntity add(@RequestBody ContentType type) {
System.out.println(type.toString());
        contentTypeService.addType(type);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/editType")
    @ApiOperation("修改类型")
    public ResponseEntity editType(@RequestBody ContentType type) {
        System.out.println(type.toString());
        contentTypeService.updateType(type);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping(value = "/getType")
    @ApiOperation("模糊查询")
    public ResponseEntity getType(@RequestBody ContentType type) {
        List<ContentType> result = contentTypeService.getType(type.getName());
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllType")
    @ApiOperation("显示所有")
    public ResponseEntity getAllType() {
        List<ContentType> result = contentTypeService.findAll();
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @DeleteMapping (value = "/delType/{id}")
    @ApiOperation("删除类型")
    public ResponseEntity delMsg(@PathVariable Long id) {
        contentTypeService.deleteForId(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
