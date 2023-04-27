package com.acme.repositories;


import com.acme.entities.Tool;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ToolRepository implements PanacheRepositoryBase<Tool, Integer> {
}
