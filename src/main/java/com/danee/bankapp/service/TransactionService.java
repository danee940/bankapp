package com.danee.bankapp.service;

import com.danee.bankapp.model.Account;
import com.danee.bankapp.model.Transaction;
import com.danee.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public void transfer(Account source, Account target, double amount) {
        BigDecimal transferAmount = BigDecimal.valueOf(amount);
        if (source.getBalance().compareTo(transferAmount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        source.setBalance(source.getBalance().subtract(transferAmount));
        target.setBalance(target.getBalance().add(transferAmount));
        accountService.save(source);
        accountService.save(target);

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(source);
        transaction.setTargetAccount(target);
        transaction.setAmount(transferAmount);
        transaction.setType(Transaction.Type.DEBIT);
        transactionRepository.save(transaction);
    }

    public Iterable<Transaction> findByAccount(Account account) {
        return transactionRepository.findBySourceAccountOrTargetAccount(account, account);
    }
}


