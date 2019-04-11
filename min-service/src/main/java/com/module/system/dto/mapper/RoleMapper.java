package com.module.system.dto.mapper;

import com.mapper.EntityMapper;
import com.module.system.dto.RoleDTO;
import com.module.system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class, MenuMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

}
