package kh.edu.cstad.modilebankingaba.init;

import jakarta.annotation.PostConstruct;
import kh.edu.cstad.modilebankingaba.domain.AccountType;
import kh.edu.cstad.modilebankingaba.repository.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountTypeInitialize {

    private final AccountTypeRepository accountTypeRepository;

    @PostConstruct
    public void init() {
        if (accountTypeRepository.count() == 0) {
            AccountType payroll = new AccountType();
            payroll.setTypeName("PAYROLL");
            payroll.setDeleted(false);

            AccountType saving = new AccountType();
            saving.setTypeName("SAVING");
            saving.setDeleted(false);

            AccountType junior = new AccountType();
            junior.setTypeName("JUNIOR");
            junior.setDeleted(false);

            accountTypeRepository.saveAll(List.of(payroll, saving, junior));
        }
    }
}
