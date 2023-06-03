package com.danee.bankapp.repository;

import com.danee.bankapp.model.Account;
import com.danee.bankapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Iterable<Account> findByUser(User user);
}

