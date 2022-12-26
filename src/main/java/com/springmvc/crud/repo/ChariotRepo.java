package com.springmvc.crud.repo;

import org.springframework.data.repository.CrudRepository;

public interface Chariot extends CrudRepository<Chariot, Long> {
Iterable<Chariot> findByUserID(long userId);
}
