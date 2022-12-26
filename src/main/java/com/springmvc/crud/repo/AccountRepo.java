package com.springmvc.crud.repo;

import com.springmvc.crud.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepo extends CrudRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findOneByEmailIgnoreCaseAndPassword(String email, String password);

    @Query(value = "SELECT * FROM tbl_account u WHERE u.status = 1", nativeQuery = true)
    Iterable<Account> findAllAccountStatusByOne();
    @Query(value = "SELECT * FROM tbl_account u WHERE u.status = 0", nativeQuery = true)
    Iterable<Account> findAllAccountStatusByZero();
}
