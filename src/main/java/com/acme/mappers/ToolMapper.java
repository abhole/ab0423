package com.acme.mappers;

import com.acme.dto.ToolDto;
import com.acme.entities.Tool;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(config=MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ToolMapper {
    @Named("entityToDto")
    ToolDto entityToDto(Tool entity);
    @IterableMapping(qualifiedByName="entityToDto")
    List<ToolDto> entityToDto(Iterable<Tool> entities);

    @Named("dtoToEntity")
    Tool dtoToEntity(ToolDto dto);

    @IterableMapping(qualifiedByName="dtoToEntity")
    List<Tool> dtoToEntity(Iterable<ToolDto> dtos);

}
