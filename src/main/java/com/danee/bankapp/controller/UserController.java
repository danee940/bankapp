package com.danee.bankapp.controller;

import com.danee.bankapp.model.Account;
import com.danee.bankapp.model.User;
import com.danee.bankapp.repository.UserRepository;
import com.danee.bankapp.service.AccountService;
import com.danee.bankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout-success")
    public String logout() {
        return "logout";
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        User user = (User) ((Authentication) principal).getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/create-account")
    public String createAccountForm(Model model) {
        model.addAttribute("account", new Account());
        return "create-account";
    }

    @PostMapping("/create-account")
    public String createAccount(@ModelAttribute Account account, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        account.setUser(user);
        accountService.save(account);
        return "redirect:/user";
    }
}


