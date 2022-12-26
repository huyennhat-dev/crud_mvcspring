package com.springmvc.crud.repo;

import com.springmvc.crud.model.Chariot;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChariotRepo extends CrudRepository<Chariot, Long> {
Optional<Chariot> findByProductIDAndUserID(long productID, long userID);
Iterable<Chariot> findAllByUserID(long id);
}
