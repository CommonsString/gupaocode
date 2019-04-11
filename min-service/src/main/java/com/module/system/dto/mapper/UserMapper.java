package com.module.system.dto.mapper;

import com.mapper.EntityMapper;
import com.module.system.dto.UserDTO;
import com.module.system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {RoleMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
