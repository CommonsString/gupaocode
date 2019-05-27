package com.module.system.dto.translation;

import com.mapper.EntityMapper;
import com.module.system.domain.Permission;
import com.module.system.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionTranslation extends EntityMapper<PermissionDTO, Permission> {

}
