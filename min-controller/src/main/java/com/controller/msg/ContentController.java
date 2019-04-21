package com.controller.msg;

import com.module.system.entity.Content;
import com.module.system.entity.vo.ContentVo;
import com.module.system.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("content")
@Api(tags = {"新闻编辑"})
public class ContentController {


    @Autowired
    private ContentService contentService;


    @PostMapping (value = "/saveMsg")
    @ApiOperation("保存新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "新闻标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "role", value = "阅读权限", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "新闻内容", required = true, dataType = "String")
    })
//    @PreAuthorize("")
    public ResponseEntity saveMsg(String title, String role, String content) {
        Content msg = new Content();
        msg.setTitle(title);
        msg.setPermission(role);
        msg.setContext(content);
        //  存储
        contentService.saveContent(msg);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getMsgTen")
    @ApiOperation("获取最新10条代审阅文章")
    public ResponseEntity gitMsgLimitTen() {
        List<ContentVo> result = this.contentService.findMsgLimit();
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
