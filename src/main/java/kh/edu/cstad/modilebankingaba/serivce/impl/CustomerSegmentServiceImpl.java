//package kh.edu.cstad.modilebankingaba.serivce.impl;
//
//import kh.edu.cstad.modilebankingaba.domain.CustomerSegment;
//import kh.edu.cstad.modilebankingaba.dto.CustomerSegmentRequest;
//import kh.edu.cstad.modilebankingaba.dto.CustomerSegmentResponse;
//import kh.edu.cstad.modilebankingaba.repository.CustomerSegmentRepository;
//import kh.edu.cstad.modilebankingaba.serivce.CustomerSegmentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//@Service
//@RequiredArgsConstructor
//public class CustomerSegmentServiceImpl implements CustomerSegmentService {
//    private final CustomerSegmentRepository customerSegmentRepository;
//
//    @Override
//    public CustomerSegmentResponse createCustomerSegment(CustomerSegmentRequest request) {
//        if (request.segmentName() == null || request.segmentName().trim().isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Segment name is required");
//        }
//
//        if (customerSegmentRepository.existsBySegmentName(request.segmentName())) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Segment name already exists: " + request.segmentName());
//        }
//
//        CustomerSegment customerSegment = new CustomerSegment();
//        customerSegment.setSegmentName(request.segmentName());
//        customerSegment.setIsDeleted(false);
//
//        customerSegment = customerSegmentRepository.save(customerSegment);
//
//        return new CustomerSegmentResponse(
//                customerSegment.getId(),
//                customerSegment.getSegmentName(),
//                customerSegment.getIsDeleted()
//        );
//    }
//}