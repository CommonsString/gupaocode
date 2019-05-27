package com.module.system.dto.translation;

import com.mapper.EntityMapper;
import com.module.system.domain.User;
import com.module.system.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {RoleTranslation.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserTranslation extends EntityMapper<UserDTO, User> {
}
