package com.module.system.service;

import com.module.system.domain.ContentType;

import java.util.List;

public interface ContentTypeService {
    void addType(ContentType type);

    List<ContentType> findAll();

    List<ContentType> getType(String name);

    void deleteForId(Long id);

    void updateType(ContentType type);
}
