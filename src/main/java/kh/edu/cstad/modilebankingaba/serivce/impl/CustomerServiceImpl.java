package kh.edu.cstad.modilebankingaba.serivce.impl;

import kh.edu.cstad.modilebankingaba.domain.Customer;
import kh.edu.cstad.modilebankingaba.domain.CustomerSegment;
import kh.edu.cstad.modilebankingaba.domain.KYC;
import kh.edu.cstad.modilebankingaba.dto.CreateCustomerRequest;
import kh.edu.cstad.modilebankingaba.dto.ResponseCustomer;
import kh.edu.cstad.modilebankingaba.dto.UpdateCustomer;
import kh.edu.cstad.modilebankingaba.mapper.CustomerMapper;
import kh.edu.cstad.modilebankingaba.repository.CustomerRepository;
import kh.edu.cstad.modilebankingaba.repository.CustomerSegmentRepository;
import kh.edu.cstad.modilebankingaba.repository.KycRepository;
import kh.edu.cstad.modilebankingaba.serivce.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final KycRepository kycRepository;
    private final CustomerSegmentRepository customerSegmentRepository;

    @Transactional
    @Override
    public void disableByPhoneNumber(String phoneNumber) {
        if (!customerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Customer with phone number " + phoneNumber + " does not exist"
            );
        }
        customerRepository.disableByPhoneNumber(phoneNumber);
    }

    @Override
    public ResponseCustomer findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(customerMapper::mapResponseCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email Not Found"));
    }

    @Override
    public ResponseCustomer findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber)
                .map(customerMapper::mapResponseCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Phone number not found"));
    }

    @Override
    public List<ResponseCustomer> findAllCustomers(Boolean isDelete) {
        return customerRepository.findAll().stream()
                .filter(customer -> customer.getIsDeleted() == isDelete)
                .map(customerMapper::mapResponseCustomer)
                .toList();
    }

    @Override
    @Transactional
    public ResponseCustomer createCustomer(CreateCustomerRequest createCustomerRequest) {
        if (customerRepository.existsByEmail(createCustomerRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Customer already exists with email: " + createCustomerRequest.email()
            );
        }

        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Customer already exists with phone number: " + createCustomerRequest.phoneNumber()
            );
        }

        Customer customer = customerMapper.frmCreateCustomer(createCustomerRequest);
        if (createCustomerRequest.customerSegmentId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer segment ID is required");
        }

        CustomerSegment customerSegment = customerSegmentRepository.findById(createCustomerRequest.customerSegmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer segment not found"));

        customer.setCustomerSegment(customerSegment);
        customer.setIsDeleted(false);


        KYC kyc = new KYC();
        kyc.setCustomer(customer);
        kyc.setIsVerified(false);
        customer.setKyc(kyc);
        customer = customerRepository.save(customer);
        return customerMapper.mapResponseCustomer(customer);
    }

    @Override
    @Transactional
    public ResponseCustomer updateCustomer(String phoneNumber, UpdateCustomer updateCustomer) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        customerMapper.updateCustomer(updateCustomer, customer);
        customer = customerRepository.save(customer);
        return customerMapper.mapResponseCustomer(customer);
    }

    @Override
    @Transactional
    public boolean deleteByCustomerNumber(String customerNumber) {
        return customerRepository.findByPhoneNumber(customerNumber)
                .map(customer -> {
                    customer.setIsDeleted(true);
                    customerRepository.save(customer);
                    return true;
                })
                .orElse(false);
    }
}