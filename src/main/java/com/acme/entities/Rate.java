package com.acme.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rates")
public class Rate {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Column(name = "tool_type")
    private String toolType;
    @Column(name = "daily_charge")
    private double dailyCharge;
    @Column(name = "weekday_charge")
    private boolean weekdayCharge;
    @Column(name = "weekend_charge")
    private boolean weekendCharge;
    @Column(name = "holiday_charge")
    private boolean holidayCharge;
}
