package com.danee.bankapp.controller;

import com.danee.bankapp.model.Account;
import com.danee.bankapp.model.Transaction;
import com.danee.bankapp.service.AccountService;
import com.danee.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


@Controller
public class TransactionController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transfer")
    public String transferForm(Principal principal, Model model) {
        String username = principal.getName();
        Iterable<Account> accounts = accountService.findAccountsByUsername(username);
        model.addAttribute("accounts", accounts);
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(Principal principal,
            @RequestParam("sourceAccountId") Long sourceAccountId,
            @RequestParam("targetAccountId") Long targetAccountId,
            @RequestParam("amount") double amount) {
        Account sourceAccount = accountService.findById(sourceAccountId);
        Account targetAccount = accountService.findById(targetAccountId);
        if (sourceAccount == null || targetAccount == null) {
            throw new RuntimeException("Invalid account ID");
        }
        transactionService.transfer(sourceAccount, targetAccount, amount);
        return "redirect:/balance";
    }

    @GetMapping("/history")
    public String history(Principal principal,
            @RequestParam("accountId") Long accountId,
            Model model) {
        Account account = accountService.findById(accountId);
        if (account == null) {
            throw new RuntimeException("Invalid account ID");
        }
        Iterable<Transaction> transactions = transactionService.findByAccount(account);
        model.addAttribute("transactions", transactions);
        return "history";
    }
}
