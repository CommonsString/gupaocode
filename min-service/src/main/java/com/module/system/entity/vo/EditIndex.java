package com.module.system.entity.vo;

import com.module.system.domain.ContentType;
import com.module.system.domain.ReadPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EditIndex {
    List<ReadPermissionVo> readPermission;

    List<ContentTypeVo> contentType;
}
