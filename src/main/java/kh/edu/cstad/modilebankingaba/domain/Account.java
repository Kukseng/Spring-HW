//package kh.edu.cstad.modilebankingaba.domain;
//
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//public class Account {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(unique = true, nullable = false)
//    private String actNo;
//
//    @Column(length = 50)
//    private BigDecimal balance;
//
//    @Column(length = 50)
//    private BigDecimal overLimit;
//
//    @Column( length = 15)
//    private Boolean isDeleted = false;
//
//    @ManyToOne
//    @JoinColumn(name = "cust_id")
//    private Customer customer;
//
//    @ManyToOne
//    private AccountType accountType;
//
//}
//
//

package kh.edu.cstad.modilebankingaba.domain;
import jakarta.persistence.*;
import kh.edu.cstad.modilebankingaba.domain.AccountType;
import kh.edu.cstad.modilebankingaba.domain.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 32)
    private String actNo;

    @Column(nullable = false, length = 50)
    private String actName;

    @Column(nullable = false, length = 15)
    private String actCurrency;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private BigDecimal overLimit;

    @Column(nullable = false)
    private Boolean isHide;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(nullable = false, name = "cust_id", referencedColumnName = "id")
    private Customer customer; // cust_id

    @ManyToOne(optional = false)
    private AccountType accountType;

}