package com.controller.msg;

import com.module.system.entity.vo.ContentVo;
import com.module.system.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("content")
@Api(tags = {"新闻编辑"})
public class ContentController {


    @Autowired
    private ContentService contentService;


    @PostMapping (value = "/saveMsg")
    @ApiOperation("编辑新闻")
    @ApiImplicitParam(name = "content", value = "新闻内容", required = true, dataType = "Content")
    public ResponseEntity saveMsg(@RequestBody ContentVo content) {
System.out.println(content.toString());
        contentService.updateContent(content);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getMsgTen")
    @ApiOperation("获取最新10条代审阅文章")
    public ResponseEntity gitMsgLimitTen() {
        List<ContentVo> result = contentService.findMsgLimit();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(value = "/upMsgFile")
    @ApiOperation("上传文章")
    public ResponseEntity upMsgFile(MultipartFile file) {
        System.out.println(file);
        return new ResponseEntity("哈哈哈", HttpStatus.OK);
    }


    @PutMapping(value = "/updateMainIndex/{id}")
    @ApiOperation("首页：记录通过")
    @ApiImplicitParam(name = "id", value = "新闻id", required = true, dataType = "String")
    public ResponseEntity updateMainIndex(@PathVariable Long id) {
        contentService.updateMainIndexMsg(id);
        return new ResponseEntity(HttpStatus.OK);
    }



    @PostMapping (value = "/saveMsgCotent")
    @ApiOperation("保存新闻")
    @ApiImplicitParam(name = "content", value = "新闻内容", required = true, dataType = "Content")
    public ResponseEntity saveMsgCotent(@RequestBody ContentVo content) {
System.out.println(content.toString());
        contentService.saveContent(content);
        return new ResponseEntity(HttpStatus.OK);
    }


}
