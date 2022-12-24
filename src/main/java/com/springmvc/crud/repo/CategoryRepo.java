package com.springmvc.crud.repo;

import com.springmvc.crud.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
}
