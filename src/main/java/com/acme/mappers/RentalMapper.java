package com.acme.mappers;

import com.acme.dto.RentalDto;
import com.acme.entities.Rental;
import org.mapstruct.*;

import java.text.NumberFormat;
import java.util.List;

@Mapper(config=MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RentalMapper {
    @Named("entityToDto")
    @Mapping(source="checkoutDate", target = "checkoutDate", dateFormat = "MM/dd/yy")
    @Mapping(source="dueDate", target = "dueDate", dateFormat = "MM/dd/yy")
    @Mapping(source = "dailyRentalCharge", target = "dailyRentalCharge", qualifiedByName = "doubleToCurrencyString")
    @Mapping(source = "preDiscountCharge", target = "preDiscountCharge", qualifiedByName = "doubleToCurrencyString")
    @Mapping(source = "discountAmount", target = "discountAmount", qualifiedByName = "doubleToCurrencyString")
    @Mapping(source = "finalCharge", target = "finalCharge", qualifiedByName = "doubleToCurrencyString")
    RentalDto entityToDto(Rental entity);
    @IterableMapping(qualifiedByName="entityToDto")
    @Mapping(source="checkoutDate",  target = "checkoutDate", dateFormat = "MM/dd/yy")
    @Mapping(source="dueDate", target = "dueDate", dateFormat = "MM/dd/yy")
    @Mapping(source = "dailyRentalCharge", target = "dailyRentalCharge", qualifiedByName = "doubleToCurrencyString")
    @Mapping(source = "preDiscountCharge", target = "preDiscountCharge", qualifiedByName = "doubleToCurrencyString")
    @Mapping(source = "discountAmount", target = "discountAmount", qualifiedByName = "doubleToCurrencyString")
    @Mapping(source = "finalCharge", target = "finalCharge", qualifiedByName = "doubleToCurrencyString")
    List<RentalDto> entityToDto(Iterable<Rental> entities);

    @Named("dtoToEntity")
    @Mapping(target = "checkoutDate", dateFormat = "MM/dd/yy")
    @Mapping(target = "dueDate", dateFormat = "MM/dd/yy")
    Rental dtoToEntity(RentalDto dto);

    @IterableMapping(qualifiedByName="dtoToEntity")
    @Mapping(target = "checkoutDate", dateFormat = "MM/dd/yy")
    @Mapping(target = "dueDate", dateFormat = "MM/dd/yy")
    List<Rental> dtoToEntity(Iterable<RentalDto> dtos);


    @Named("doubleToCurrencyString")
    default String doubleToCurrencyString(double value) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(value);
    }
}
