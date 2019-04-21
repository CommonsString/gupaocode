package com.module.system.repository;

import com.module.system.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long>, JpaSpecificationExecutor {

    @Query(value = "select * FROM msg_content where is_right = '0' ORDER BY create_time desc limit 10", nativeQuery = true)
    List<Content> findByIsRightDesc();
}
