package com.springmvc.crud.repo;

import com.springmvc.crud.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepo extends CrudRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findOneByEmailIgnoreCaseAndPassword(String email, String password);

}
