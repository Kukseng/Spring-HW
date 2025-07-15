package kh.edu.cstad.modilebankingaba.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class KYC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Boolean isVerified;


    @OneToOne(optional = false)
    @JoinColumn(name = "cust_id")
    private Customer customer;
}

