package kh.edu.cstad.modilebankingaba.domain;


import jakarta.persistence.*;
import kh.edu.cstad.modilebankingaba.domain.Customer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer_segments")
public class CustomerSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, length = 100)
    private String segmentName;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(length = 100)
    private String description;

    @OneToMany(mappedBy = "customerSegment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Customer> customers;
}
