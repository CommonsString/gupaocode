package com.module.system.repository;

import com.module.system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor {

    /**
     * findByName
     * @param name
     * @return
     */
    Role findByName(String name);

    /**
     * userid-->roleList
     * findByUsers_Id
     * @param id
     * @return
     */
    Set<Role> findByUsers_Id(Long id);
}
