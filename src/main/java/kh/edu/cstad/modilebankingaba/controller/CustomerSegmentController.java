//package kh.edu.cstad.modilebankingaba.controller;
//
//import kh.edu.cstad.modilebankingaba.dto.CustomerSegmentRequest;
//import kh.edu.cstad.modilebankingaba.dto.CustomerSegmentResponse;
//import kh.edu.cstad.modilebankingaba.serivce.CustomerSegmentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/customer-segments")
//@RequiredArgsConstructor
//public class CustomerSegmentController {
//    private final CustomerSegmentService customerSegmentService;
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public CustomerSegmentResponse createCustomerSegment(@RequestBody CustomerSegmentRequest request) {
//
//        return customerSegmentService.createCustomerSegment(request);
//
//    }
//}