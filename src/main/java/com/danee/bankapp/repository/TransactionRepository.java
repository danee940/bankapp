package com.danee.bankapp.repository;

import com.danee.bankapp.model.Account;
import com.danee.bankapp.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Iterable<Transaction> findBySourceAccountOrTargetAccount(Account sourceAccount, Account targetAccount);
}

