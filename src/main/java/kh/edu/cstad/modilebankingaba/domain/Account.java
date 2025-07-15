package kh.edu.cstad.modilebankingaba.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String actNo;

    @Column(length = 50)
    private BigDecimal balance;

    @Column(length = 50)
    private BigDecimal overLimit;

    @Column( length = 15)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @ManyToOne
    private AccountType accountType;

}


