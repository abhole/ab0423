package com.acme.repositories;

import com.acme.entities.Rental;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RentalRepository implements PanacheRepositoryBase<Rental, Integer> {
}
