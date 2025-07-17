package kh.edu.cstad.modilebankingaba.repository;

import kh.edu.cstad.modilebankingaba.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

    Optional<AccountType> findByTypeName(String typeName);


}
