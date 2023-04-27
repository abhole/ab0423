package com.acme.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rentals")
public class Rental {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Column(name = "tool_code")
    private String toolCode;
    @Column(name = "tool_type")
    private String toolType;
    @Column(name = "brand")
    private String brand;
    @Column(name = "rental_days")
    private Integer rentalDays;
    @Column(name = "checkout_date", columnDefinition = "DATE")
    private Date checkoutDate;
    @Column(name = "due_date", columnDefinition = "DATE")
    private Date dueDate;
    @Column(name = "daily_rental_charge")
    private Double dailyRentalCharge;
    @Column(name = "charge_days")
    private Integer chargeDays;
    @Column(name = "pre_discount_charge")
    private Double preDiscountCharge;
    @Column(name = "discount_percent")
    private Integer discountPercent;
    @Column(name = "discount_amount")
    private Double discountAmount;
    @Column(name = "final_charge")
    private Double finalCharge;
}
