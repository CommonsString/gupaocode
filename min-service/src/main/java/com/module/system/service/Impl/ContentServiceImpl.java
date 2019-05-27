package com.module.system.service.Impl;

import com.exception.EntityExistException;
import com.module.system.domain.Content;
import com.module.system.domain.ContentType;
import com.module.system.domain.ReadPermission;
import com.module.system.entity.vo.ContentVo;
import com.module.system.entity.vo.UpVo;
import com.module.system.mapper.ContentMapper;
import com.module.system.mapper.ContentTypeMapper;
import com.module.system.mapper.ReadPermissionMapper;
import com.module.system.service.ContentService;
import com.utils.excel.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ContentServiceImpl implements ContentService {


    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ContentTypeMapper contentTypeMapper;

    @Autowired
    private ReadPermissionMapper readPermissionMapper;

    /**
     * 编辑页面-保存
     *
     * @param msg
     */
    @Override
    public void updateContent(ContentVo msg) {
        // 根据名称, 查类型和阅读权限
        ContentType type = contentTypeMapper.findByName(msg.getType());
        ReadPermission per = readPermissionMapper.findByName(msg.getTag());
        Content content = new Content();
        // id
        content.setId(msg.getOdd());
        // context
        content.setContext(msg.getContent());
        // title
        content.setTitle(msg.getName());
        // isRight
        if (type != null) {
            content.setTypeId(type.getId());
        } else {
            content.setTypeId(Long.valueOf(msg.getType()));
        }
        if (per != null) {
            content.setPermission(per.getId());
        } else {
            content.setPermission(Long.valueOf(msg.getTag()));
        }
        System.out.println("新闻编辑-保存：" + content.toString());
        contentMapper.updateByPrimaryKeySelective(content);
    }

    @Override
    public List<ContentVo> findMsgLimit() {
        // 查询
        List<Content> tmp_result = contentMapper.findByIsRightDesc();
        List<ContentVo> result = new ArrayList<ContentVo>();
        tmp_result.forEach(el -> {
            ContentVo model = new ContentVo();
            model.setOdd(el.getId());
            model.setName(el.getTitle());
            // 转换
            String isRight = el.getIsRight();
            if ("0".equals(isRight)) {
                model.setStatus("未审阅");
            } else {
                model.setStatus("已审阅");
            }
            model.setTag(el.getReadName());
            model.setContent(el.getContext());
            model.setType(el.getType());
            // 加入结果集
            result.add(model);
        });
        return result;
    }


    /**
     * 主页更新状态
     *
     * @param id
     */
    @Override
    public void updateMainIndexMsg(Long id) {

        Content content = new Content();
        content.setId(id);
        content.setIsRight("1");
        contentMapper.updateByPrimaryKeySelective(content);
    }

    /**
     * 保存新闻
     *
     * @param content
     */
    @Override
    public void saveContent(ContentVo content) {
        Content con = contentMapper.findByTitle(content.getName());
        if(con != null) {
            throw new RuntimeException("标题重复, 请检查后重试!");
        }
        Content contentDomain = new Content();
        contentDomain.setTitle(content.getName());
        contentDomain.setContext(content.getContent());
        contentDomain.setPermission(Long.valueOf(content.getTag()));
        contentDomain.setTypeId(Long.valueOf(content.getType()));
        contentDomain.setCreateTime(new Date());
System.out.println("上传新闻-" + contentDomain.toString());
        contentMapper.insertSelective(contentDomain);
    }

    @Override
    public List<ContentVo> findMsglike(Content content) {
        // 查询
        if (content.getIsRight() == null && "".equals(content.getIsRight())) {
            // 默认已审阅
            content.setIsRight("1");
        }
        List<Content> tmp_result = contentMapper.findByIsRightMsg(content);
        List<ContentVo> result = new ArrayList<ContentVo>();
        tmp_result.forEach(el -> {
            ContentVo model = new ContentVo();
            model.setOdd(el.getId());
            model.setName(el.getTitle());
            // 转换
            String isRight = el.getIsRight();
            if ("0".equals(isRight)) {
                model.setStatus("未审阅");
            } else {
                model.setStatus("已审阅");
            }
            model.setTag(el.getReadName());
            model.setContent(el.getContext());
            model.setType(el.getType());
            // 加入结果集
            result.add(model);
        });
        return result;
    }

    @Override
    public List<ContentVo> findMsgAll() {
        List<Content> tmp_result = contentMapper.findAll();
        List<ContentVo> result = new ArrayList<ContentVo>();
        tmp_result.forEach(el -> {
            ContentVo model = new ContentVo();
            model.setOdd(el.getId());
            model.setName(el.getTitle());
            // 转换
            String isRight = el.getIsRight();
            if ("0".equals(isRight)) {
                model.setStatus("未审阅");
            } else {
                model.setStatus("已审阅");
            }
            model.setTag(el.getReadName());
            model.setContent(el.getContext());
            model.setType(el.getType());
            // 加入结果集
            result.add(model);
        });
        return result;
    }

    @Override
    public void updateMsgIsRight(Long id) {
        Content content = new Content();
        content.setId(id);
        content.setIsRight("0");
        contentMapper.updateByPrimaryKeySelective(content);
    }

    @Override
    public void deleteForId(Long id) {
        contentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UpVo> inportMsg(MultipartFile file) {
        if(file == null) {
            throw new RuntimeException("文件中没有数据！");
        }
        // 获取文件流
        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<UpVo> result = new ArrayList<>();
        // 数据封装操作
        try {
            // 文件名
            String fileName = file.getOriginalFilename();
            Workbook work_book = ExcelUtils.chooseWorkbook(fileName, in);
            // 获取sheet数量
            int sheets = work_book.getNumberOfSheets();
            for(int i = 0; i < sheets; i++) {
                UpVo baseInfo = new UpVo();
                List<UpVo> info = ExcelUtils.readDateListT(work_book, baseInfo, 2, 0, i);
                // 新闻查重
                info.forEach(msg -> {
                    Content isCopy = contentMapper.findByTitle(msg.getTitle());
                    // 移除
                    if(isCopy != null) {
                        info.remove(msg);
                    }
                });
                // 所有结果
                result.addAll(info);
    System.out.println(info.toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
