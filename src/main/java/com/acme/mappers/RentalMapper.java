package com.acme.mappers;

import com.acme.dto.RentalDto;
import com.acme.entities.Rental;
import org.mapstruct.*;

import java.util.List;

@Mapper(config=MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RentalMapper {
    @Named("entityToDto")
    @Mapping(source="checkoutDate", target = "checkoutDate", dateFormat = "MM/dd/yy")
    @Mapping(source="dueDate", target = "dueDate", dateFormat = "MM/dd/yy")
    @Mapping(source = "dailyRentalCharge", target = "dailyRentalCharge", numberFormat = "$#.00")
    @Mapping(source = "preDiscountCharge", target = "preDiscountCharge", numberFormat = "$#.00")
    @Mapping(source = "discountAmount", target = "discountAmount", numberFormat = "$#.00")
    @Mapping(source = "finalCharge", target = "finalCharge", numberFormat = "$#.00")
    RentalDto entityToDto(Rental entity);
    @IterableMapping(qualifiedByName="entityToDto")
    @Mapping(source="checkoutDate",  target = "checkoutDate", dateFormat = "MM/dd/yy")
    @Mapping(source="dueDate", target = "dueDate", dateFormat = "MM/dd/yy")
    @Mapping(source = "dailyRentalCharge", target = "dailyRentalCharge", numberFormat = "$#.00")
    @Mapping(source = "preDiscountCharge", target = "preDiscountCharge", numberFormat = "$#.00")
    @Mapping(source = "discountAmount", target = "discountAmount", numberFormat = "$#.00")
    @Mapping(source = "finalCharge", target = "finalCharge", numberFormat = "$#.00")
    List<RentalDto> entityToDto(Iterable<Rental> entities);

    @Named("dtoToEntity")
    @Mapping(target = "checkoutDate", dateFormat = "MM/dd/yy")
    @Mapping(target = "dueDate", dateFormat = "MM/dd/yy")
    Rental dtoToEntity(RentalDto dto);

    @IterableMapping(qualifiedByName="dtoToEntity")
    @Mapping(target = "checkoutDate", dateFormat = "MM/dd/yy")
    @Mapping(target = "dueDate", dateFormat = "MM/dd/yy")
    List<Rental> dtoToEntity(Iterable<RentalDto> dtos);
}
