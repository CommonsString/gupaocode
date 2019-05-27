package com.module.system.dto.translation;

import com.mapper.EntityMapper;
import com.module.system.domain.Role;
import com.module.system.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {PermissionTranslation.class, MenuTranslation.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleTranslation extends EntityMapper<RoleDTO, Role> {

}
