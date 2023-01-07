package com.springmvc.crud.repo;

import com.springmvc.crud.model.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ProductRepo extends CrudRepository<Product, Long> {
    Iterable<Product> findAllByStatus(int status);
    @Query(value = "SELECT * FROM tbl_product u WHERE u.product_name LIKE %:productName%", nativeQuery = true)
    Iterable<Product> findAllProduct(String productName);

    Optional<Product> findByIdAndStatus(long id , int status);
}
