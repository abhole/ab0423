package com.acme.services;

import com.acme.entities.Rate;
import com.acme.entities.Rental;
import com.acme.entities.Tool;
import com.acme.repositories.RentalRepository;
import com.acme.repositories.ToolRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

@ApplicationScoped
public class RentalService implements GenericService<Rental> {

    private static final Logger LOG = Logger.getLogger(RentalService.class);
    private final RentalRepository repository;
    private final ToolService toolService;
    private final RateService rateService;

    @Inject
    public RentalService(RentalRepository repository, ToolService toolService, RateService rateService) {
        this.repository = repository;
        this.toolService = toolService;
        this.rateService = rateService;
    }

    @Override
    public List<Rental> findAll() {
        return repository.listAll();
    }

    @Transactional
    public void save(Rental entity) {
        // tool type
        Optional<Tool> optionalTool = toolService.findByToolCode(entity.getToolCode());
        if (optionalTool.isPresent()) {
            entity.setToolType(optionalTool.get().getToolType());
        } else {
            throw new BadRequestException("Invalid tool code");
        }

        //brand
        entity.setBrand(optionalTool.get().getBrand());

        //due date
        Date checkoutDate = entity.getCheckoutDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);
        calendar.add(Calendar.DATE, entity.getRentalDays());
        Date dueDate = calendar.getTime();
        entity.setDueDate(dueDate);


        // daily charge
        Optional<Rate> rateOptional = rateService.findByToolType(optionalTool.get().getToolType());
        if (rateOptional.isPresent()) {
            entity.setDailyRentalCharge(rateOptional.get().getDailyCharge());
        } else {
            throw new BadRequestException("Invalid tool type");
        }

        // count charge days
        Map<String, Integer> daysByTypeMap = calculateDaysByType(checkoutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        int chargeDays=0;
        Rate rate = rateOptional.get();
        for (String key:daysByTypeMap.keySet()) {
            int days = daysByTypeMap.get(key);
            switch (key) {
                case "weekends" -> {
                    if (rate.isWeekendCharge()) {
                        chargeDays += days;
                    }
                }
                case "weekdays" -> {
                    if (rate.isWeekdayCharge()) {
                        chargeDays += days;
                    }
                }
                case "holidays" -> {
                    if (rate.isHolidayCharge()) {
                        chargeDays += days;
                    }
                }
            }
        }
        entity.setChargeDays(chargeDays);

        // pre-discount charge = Calculated as charge days X daily charge
        double preDiscountCharge = entity.getChargeDays() * rate.getDailyCharge();
        BigDecimal pdc = new BigDecimal(preDiscountCharge).setScale(2, RoundingMode.HALF_UP);
        entity.setPreDiscountCharge(pdc.doubleValue());

        // discount amount
        double discountAmount = (entity.getDiscountPercent() * preDiscountCharge)/100;
        BigDecimal da = new BigDecimal(discountAmount).setScale(2, RoundingMode.HALF_UP);
        entity.setDiscountAmount(da.doubleValue());

        // final charge
        double finalCharge = preDiscountCharge - discountAmount;
        BigDecimal fc = new BigDecimal(finalCharge).setScale(2, RoundingMode.HALF_UP);
        entity.setFinalCharge(fc.doubleValue());

        repository.persistAndFlush(entity);
    }

    private Map<String, Integer> calculateDaysByType(LocalDate startDate, LocalDate endDate) {
        Map<String, Integer> daysByType = new HashMap<>();
        int weekdays = 0;
        int weekends = 0;
        int holidays = 0;

        LocalDate currentDate = startDate;
        // skip first day to start count of total rental days
        currentDate = currentDate.plus(1, ChronoUnit.DAYS);

        while(!currentDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            Month month = currentDate.getMonth();
            int dayOfMonth = currentDate.getDayOfMonth();
            if (month.equals(Month.JULY) && dayOfMonth==4) { // Independence Day
                if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                    weekends++;
                    currentDate = currentDate.plus(1, ChronoUnit.DAYS);
                }
                holidays++;
            } else if (month.equals(Month.SEPTEMBER) && dayOfWeek==DayOfWeek.MONDAY && dayOfMonth <= 7) { // Labor Day
                holidays++;
            } else if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                weekends++;
            } else {
                weekdays++;
            }
            currentDate = currentDate.plus(1, ChronoUnit.DAYS);
        }
        LOG.debug("Weekdays: " + weekdays);
        LOG.debug("Weekends: " + weekends);
        LOG.debug("Holidays: " + holidays);

        daysByType.put("weekdays", weekdays);
        daysByType.put("weekends", weekends);
        daysByType.put("holidays", holidays);

        return daysByType;
    }

}
