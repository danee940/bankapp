package com.danee.bankapp.controller;

import com.danee.bankapp.model.Account;
import com.danee.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/balance")
    public String balance(Principal principal, Model model) {
        String username = principal.getName();
        Iterable<Account> accounts = accountService.findAccountsByUsername(username);
        model.addAttribute("accounts", accounts);
        return "balance";
    }
}

