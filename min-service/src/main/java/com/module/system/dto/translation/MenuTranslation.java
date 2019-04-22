package com.module.system.dto.translation;

import com.mapper.EntityMapper;
import com.module.system.domain.Menu;
import com.module.system.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuTranslation extends EntityMapper<MenuDTO, Menu> {

}
