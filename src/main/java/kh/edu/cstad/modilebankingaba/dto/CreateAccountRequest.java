package kh.edu.cstad.modilebankingaba.dto;

import kh.edu.cstad.modilebankingaba.util.CurrencyUtil;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String actNo,
        String actName,
        CurrencyUtil actCurrency,
        BigDecimal balance,
        Integer customerId,
        String accountType,
        String phoneNumber
) {
}
