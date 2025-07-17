package kh.edu.cstad.modilebankingaba.serivce.impl;

import jakarta.transaction.Transactional;
import kh.edu.cstad.modilebankingaba.domain.Account;
import kh.edu.cstad.modilebankingaba.domain.AccountType;
import kh.edu.cstad.modilebankingaba.domain.Customer;
import kh.edu.cstad.modilebankingaba.dto.CreateAccountRequest;
import kh.edu.cstad.modilebankingaba.dto.ResponseAccount;
import kh.edu.cstad.modilebankingaba.dto.UpdateAccount;
import kh.edu.cstad.modilebankingaba.mapper.AccountMapper;
import kh.edu.cstad.modilebankingaba.repository.AccountRepository;
import kh.edu.cstad.modilebankingaba.repository.AccountTypeRepository;
import kh.edu.cstad.modilebankingaba.repository.CustomerRepository;
import kh.edu.cstad.modilebankingaba.repository.CustomerSegmentRepository;
import kh.edu.cstad.modilebankingaba.serivce.AccountService;
import kh.edu.cstad.modilebankingaba.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;

    private final CustomerSegmentRepository customerSegmentRepository;

    @Override
    public ResponseAccount getAccountByActNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        return accountMapper.toResponse(account);
    }



    @Override
    public ResponseAccount createAccount(CreateAccountRequest createAccountRequest) {
        Account account = new Account();
        if (accountRepository.existsByActNo(createAccountRequest.actNo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account already exists");
        }



        AccountType accountType = accountTypeRepository
                .findByTypeName(createAccountRequest.accountType())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account Type Not Found"));

        // Validation customer phone number
        Customer customer = customerRepository
                .findByPhoneNumber(createAccountRequest.phoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer phone number not found"));

        switch (createAccountRequest.actCurrency()) {
            case CurrencyUtil.USD -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.valueOf(10)) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be greater than 10 USD");
                }
                // Set over limit base on customer segment
                if (customer.getCustomerSegment().getSegmentName().equals("REGULAR")) {
                    account.setOverLimit(BigDecimal.valueOf(5000));
                } else if (customer.getCustomerSegment().getSegmentName().equals("SILVER")) {
                    account.setOverLimit(BigDecimal.valueOf(10000));
                } else {
                    account.setOverLimit(BigDecimal.valueOf(50000));
                }
            }
            case CurrencyUtil.KHR -> {
                if (createAccountRequest.balance().compareTo(BigDecimal.valueOf(40000)) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance must be greater than 40,000 KHR");
                }

                if (customer.getCustomerSegment().getSegmentName().equals("REGULAR")) {
                    account.setOverLimit(BigDecimal.valueOf(5000 * 4000));
                } else if (customer.getCustomerSegment().getSegmentName().equals("SILVER")) {
                    account.setOverLimit(BigDecimal.valueOf(10000 * 4000));
                } else {
                    account.setOverLimit(BigDecimal.valueOf(50000 * 4000));
                }
            }
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Currency is not supported");
        }

        // Validate account no
        if (createAccountRequest.actNo() != null) {
            if (accountRepository.existsByActNo(createAccountRequest.actNo())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Account with Act No %s already exists", createAccountRequest.actNo()));
            }
            account.setActNo(createAccountRequest.actNo());
        } else {
            String actNo;
            do {
                actNo = String.format("%09d", new Random().nextInt(1_000_000_000)); // Max: 999,999,999
            } while (accountRepository.existsByActNo(actNo));
            account.setActNo(actNo);
        }

        account.setActName(createAccountRequest.actName());
        account.setActCurrency(createAccountRequest.actCurrency().name());
        account.setBalance(createAccountRequest.balance());
        account.setIsHide(false);
        account.setIsDeleted(false);
        account.setCustomer(customer);
        account.setAccountType(accountType);

        account = accountRepository.save(account);

        return accountMapper.toResponse(account);
    }
    @Override
    public List<ResponseAccount> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    public ResponseAccount updateAccount(String actNo, UpdateAccount updateAccountRequest) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        accountMapper.toAccountPartially(updateAccountRequest, account);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public List<ResponseAccount> findByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return accountRepository.findByCustomer(customer).stream()
                .map(accountMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public boolean deleteByActNo(String actNo) {
        if (!accountRepository.existsByActNo(actNo)) return false;
        accountRepository.deleteByActNo(actNo);
        return true;
    }

    @Override
    public boolean disableByActNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        account.setIsDeleted(true);
        accountRepository.save(account);
        return true;
    }
    }



