package kh.edu.cstad.modilebankingaba.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kh.edu.cstad.modilebankingaba.domain.AccountType;
import kh.edu.cstad.modilebankingaba.domain.Customer;

import java.math.BigDecimal;

public record ResponseAccount(
        BigDecimal balance,
        BigDecimal overLimit,
        String customerName,
        String customerPhone
) {
}
//@Column(unique = true, nullable = false)
//private String actNo;
//
//@Column(length = 50)
//private BigDecimal balance;
//
//@Column(length = 50)
//private BigDecimal overLimit;
//
//@Column( length = 15)
//private Boolean isDeleted = false;
//
//@ManyToOne
//@JoinColumn(name = "cust_id")
//private Customer customer;
//
//@ManyToOne
//private AccountType accountType;