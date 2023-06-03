package com.danee.bankapp.service;

import com.danee.bankapp.model.Account;
import com.danee.bankapp.model.User;
import com.danee.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    public Iterable<Account> findAccountsByUsername(String username) {
        User user = userService.findByUsername(username);
        return accountRepository.findByUser(user);
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }
}


