package com.springmvc.crud.repo;

import com.springmvc.crud.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepo extends CrudRepository<Product, Long> {
}
