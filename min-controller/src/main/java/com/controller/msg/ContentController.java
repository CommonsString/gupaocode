package com.controller.msg;

import com.module.system.domain.Content;
import com.module.system.entity.vo.ContentVo;
import com.module.system.entity.vo.UpVo;
import com.module.system.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("content")
@Api(tags = {"新闻编辑"})
public class ContentController {


    @Autowired
    private ContentService contentService;


    @PostMapping (value = "/saveMsg")
    @ApiOperation("编辑新闻页面-保存")
    @ApiImplicitParam(name = "content", value = "新闻内容", required = true, dataType = "Content")
    public ResponseEntity saveMsg(@RequestBody ContentVo content) {
System.out.println(content.toString());
        if(content.getOdd() != null) {
            contentService.updateContent(content);
        } else {
            contentService.saveContent(content);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getMsgTen")
    @ApiOperation("获取最新10条代审阅文章")
    public ResponseEntity gitMsgLimitTen() {
        List<ContentVo> result = contentService.findMsgLimit();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @ApiOperation("自动采集")
    @PostMapping(value = "/upMsgFile")
    public ResponseEntity upMsgFile(MultipartFile file) {
        List<UpVo> result = contentService.inportMsg(file);
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @PutMapping(value = "/updateMainIndex/{id}")
    @ApiOperation("首页：记录通过")
    @ApiImplicitParam(name = "id", value = "新闻id", required = true, dataType = "String")
    public ResponseEntity updateMainIndex(@PathVariable Long id) {
        contentService.updateMainIndexMsg(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/handNotPassId/{id}")
    @ApiOperation("记录不通过")
    @ApiImplicitParam(name = "id", value = "新闻id", required = true, dataType = "String")
    public ResponseEntity hasNotPassId(@PathVariable Long id) {
        contentService.updateMsgIsRight(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping (value = "/saveMsgContent")
    @ApiOperation("新闻上传页面-保存")
    @ApiImplicitParam(name = "content", value = "新闻内容", required = true, dataType = "Content")
    public ResponseEntity saveMsgContent(@RequestBody ContentVo content) {
System.out.println(content.toString());
        contentService.saveContent(content);
        return new ResponseEntity(HttpStatus.OK);
    }



    @PostMapping(value = "/getAllMsg")
    @ApiOperation("审阅文章查询")
    public ResponseEntity getMsgFind(@RequestBody Content content) {
        System.out.println(content.toString());
        List<ContentVo> result = contentService.findMsglike(content);
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @GetMapping(value = "/getAll")
    @ApiOperation("显示所有未审阅")
    public ResponseEntity getMsgAll() {
        List<ContentVo> result = contentService.findMsgAll();
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @DeleteMapping (value = "/delMsg/{id}")
    @ApiOperation("删除新闻")
    public ResponseEntity delMsg(@PathVariable Long id) {
        contentService.deleteForId(id);
        return new ResponseEntity(HttpStatus.OK);
    }





}
