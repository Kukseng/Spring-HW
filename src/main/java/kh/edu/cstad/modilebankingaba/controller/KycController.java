package kh.edu.cstad.modilebankingaba.controller;

import kh.edu.cstad.modilebankingaba.serivce.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kyc")
@RequiredArgsConstructor
public class KycController {
    private final KycService kycService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{nationalCardId}")
    public void verifyKyc(@PathVariable String nationalCardId) {
        kycService.verifyKyc(nationalCardId);
    }
}