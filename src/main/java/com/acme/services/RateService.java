package com.acme.services;

import com.acme.entities.Rate;
import com.acme.repositories.RateRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RateService implements GenericService<Rate>{

    private final RateRepository repository;

    @Inject
    public RateService(RateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Rate> findAll() {
        return repository.listAll();
    }

    public Optional<Rate> findByToolType(String toolType) {
        return Optional.ofNullable(repository.find("toolType", toolType).firstResult());
    }
}
