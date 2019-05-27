package com.module.system.service;

import com.module.system.entity.vo.EditIndex;
import com.module.system.entity.vo.MainIndex;

import java.util.List;

public interface MsgGetService {
    List<Object> findByReadAndType();

    MainIndex getMainIndexInfo();

    EditIndex getEditIndexInfo();
}
