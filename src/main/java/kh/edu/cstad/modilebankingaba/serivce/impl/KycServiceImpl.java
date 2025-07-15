package kh.edu.cstad.modilebankingaba.serivce.impl;

import kh.edu.cstad.modilebankingaba.domain.Customer;
import kh.edu.cstad.modilebankingaba.domain.KYC;
import kh.edu.cstad.modilebankingaba.repository.CustomerRepository;
import kh.edu.cstad.modilebankingaba.repository.KycRepository;
import kh.edu.cstad.modilebankingaba.serivce.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class KycServiceImpl implements KycService {
    private final CustomerRepository customerRepository;
    private final KycRepository kycRepository;

    @Override
    @Transactional
    public void verifyKyc(String nationalCardId) {
        Customer customer = customerRepository.findByNationalCardId(nationalCardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "National Card Not Found"));
        KYC kyc = kycRepository.findByCustomer(customer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "KYC not found"));

        kyc.setIsVerified(true);
        kycRepository.save(kyc);
    }
}