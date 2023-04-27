package com.acme.services;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {
//    Optional<T> findById(String id);
    List<T> findAll();

//    void save(T entity);
//    Optional<T> updateById(String id, T entity);

//    void delete(T entity);
}
