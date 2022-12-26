package com.springmvc.crud.repo;

import com.springmvc.crud.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ProductRepo extends CrudRepository<Product, Long> {
    Iterable<Product> findAllByStatus(int status);

    Optional<Product> findByIdAndStatus(long id , int status);
}
