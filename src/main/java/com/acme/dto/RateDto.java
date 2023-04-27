package com.acme.dto;

public record RateDto(String id,
                      String toolType,
                      double dailyCharge,
                      boolean weekdayCharge,
                      boolean weekendCharge,
                      boolean holidayCharge
) {}
