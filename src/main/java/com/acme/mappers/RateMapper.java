package com.acme.mappers;

import com.acme.dto.RateDto;
import com.acme.entities.Rate;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(config=MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RateMapper {
    @Named("entityToDto")
    RateDto entityToDto(Rate entity);
    @IterableMapping(qualifiedByName="entityToDto")
    List<RateDto> entityToDto(Iterable<Rate> entities);

    @Named("dtoToEntity")
    Rate dtoToEntity(RateDto dto);

    @IterableMapping(qualifiedByName="dtoToEntity")
    List<Rate> dtoToEntity(Iterable<RateDto> dtos);

}
