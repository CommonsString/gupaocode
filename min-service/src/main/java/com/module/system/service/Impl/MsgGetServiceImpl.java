package com.module.system.service.Impl;

import com.module.system.domain.ContentType;
import com.module.system.domain.ReadPermission;
import com.module.system.entity.vo.ContentTypeVo;
import com.module.system.entity.vo.EditIndex;
import com.module.system.entity.vo.MainIndex;
import com.module.system.entity.vo.ReadPermissionVo;
import com.module.system.mapper.ContentMapper;
import com.module.system.mapper.ContentTypeMapper;
import com.module.system.mapper.ReadPermissionMapper;
import com.module.system.service.MsgGetService;
import com.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MsgGetServiceImpl implements MsgGetService {

    @Autowired
    private ReadPermissionMapper readPermissionMapper;

    @Autowired
    private ContentTypeMapper contentTypeMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<Object> findByReadAndType() {
        List<Object> result = new ArrayList();
        // 新闻类型
        List<ContentType> con = this.contentTypeMapper.selectAll();
        // 阅读权限
        List<ReadPermission> per = this.readPermissionMapper.selectAll();
        return result;
    }

    @Override
    public MainIndex getMainIndexInfo() {
        // 未审阅
        MainIndex result = new MainIndex();
        result.setIsRight(contentMapper.countIsRight());
        result.setNotIsRight(contentMapper.countNotIsRight());
        result.setNewGet(contentMapper.countMsgNow(DateUtils.toYY_mm_dd_String(new Date())));
        result.setTypeCount(contentTypeMapper.countType());
        return result;
    }

    @Override
    public EditIndex getEditIndexInfo() {
        EditIndex result = new EditIndex();
        // 新闻类型
        List<ContentType> con = this.contentTypeMapper.selectAll();
        List<ContentTypeVo> con_tmp = new ArrayList<>();
        con.forEach(el -> {
            ContentTypeVo entity1 = new ContentTypeVo();
            entity1.setValue(el.getName());
            entity1.setLabel(el.getId());
            con_tmp.add(entity1);
        });
        // 阅读权限
        List<ReadPermission> per = this.readPermissionMapper.selectAll();
        List<ReadPermissionVo> per_tmp = new ArrayList<>();
        per.forEach(el -> {
            ReadPermissionVo eneity2 = new ReadPermissionVo();
            eneity2.setValue(el.getName());
            eneity2.setLabel(el.getId());
            per_tmp.add(eneity2);
        });
        result.setContentType(con_tmp);
        result.setReadPermission(per_tmp);
        return result;
    }


}
