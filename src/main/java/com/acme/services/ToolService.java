package com.acme.services;

import com.acme.entities.Tool;
import com.acme.repositories.ToolRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ToolService implements GenericService<Tool> {

    private final ToolRepository repository;

    @Inject
    public ToolService(ToolRepository repository) {
        this.repository=repository;

    }

    @Override
    public List<Tool> findAll() {
        return repository.listAll();
    }

    public Optional<Tool> findByToolCode(String toolCode) {
        return Optional.ofNullable(repository.find("toolCode", toolCode).firstResult());
    }

}
