package kh.edu.cstad.modilebankingaba.repository;

import kh.edu.cstad.modilebankingaba.domain.Customer;
import kh.edu.cstad.modilebankingaba.dto.ResponseCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByPhoneNumber(String phoneNumber);

    @Modifying
    @Query(value = """
        UPDATE Customer as CUSTOMER
                SET CUSTOMER.isDeleted = TRUE
                        WHERE CUSTOMER.phoneNumber = ?1
        """)
     void disableByPhoneNumber(String phoneNumber);
    Optional<Customer> findByNationalCardId(String nationalCardId);

    Optional<Customer> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
