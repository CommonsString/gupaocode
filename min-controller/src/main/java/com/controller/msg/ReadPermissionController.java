package com.controller.msg;

import com.module.system.domain.ContentType;
import com.module.system.domain.ReadPermission;
import com.module.system.service.ReadPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("read")
@Api(tags = {"阅读权限"})
public class ReadPermissionController {


    @Autowired
    private ReadPermissionService readPermissionService;

    @PostMapping(value = "/getRead")
    @ApiOperation("模糊查询")
    public ResponseEntity getRead(@RequestBody ReadPermission read) {
        List<ReadPermission> result = readPermissionService.getRead(read.getName());
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @GetMapping(value = "/getAllRead")
    @ApiOperation("显示所有")
    public ResponseEntity getAllRead() {
        List<ReadPermission> result = readPermissionService.findAll();
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @PostMapping(value = "/addRead")
    @ApiOperation("新增阅读权限")
    public ResponseEntity add(@RequestBody ReadPermission read) {
System.out.println(read.toString());
        readPermissionService.addRead(read);
        return new ResponseEntity(HttpStatus.OK);
    }



    @PutMapping(value = "/editRead")
    @ApiOperation("修改阅读权限")
    public ResponseEntity editType(@RequestBody ReadPermission read) {
        readPermissionService.updateRead(read);
        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping (value = "/delRead/{id}")
    @ApiOperation("删除类型")
    public ResponseEntity delMsg(@PathVariable Long id) {
        readPermissionService.deleteForId(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
