package com.module.system.service;

import com.module.system.domain.ContentType;
import com.module.system.domain.ReadPermission;

import java.util.List;

public interface ReadPermissionService {
    List<ReadPermission> getRead(String name);

    List<ReadPermission> findAll();

    void addRead(ReadPermission read);

    void updateRead(ReadPermission read);

    void deleteForId(Long id);
}
