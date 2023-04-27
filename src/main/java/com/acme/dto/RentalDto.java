package com.acme.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RentalDto {
        private String id;
        @NotBlank
        private String toolCode;
        private String toolType;
        private String brand;
        @NotNull
        @DecimalMin("1")
        private Integer rentalDays;
        @NotNull
        private String checkoutDate;
        private String dueDate;
        private String dailyRentalCharge;
        private Integer chargeDays;
        private String preDiscountCharge;
        @NotNull
        @DecimalMin("0")
        @DecimalMax("100")
        private Integer discountPercent;
        private  String discountAmount;
        private String finalCharge;

}

