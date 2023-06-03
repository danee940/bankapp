package com.danee.bankapp.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String accountNumber;

    private String currency;

    private BigDecimal balance;
}

