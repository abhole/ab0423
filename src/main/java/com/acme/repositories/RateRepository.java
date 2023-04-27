package com.acme.repositories;

import com.acme.entities.Rate;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class RateRepository implements PanacheRepositoryBase<Rate, Integer> {
}
