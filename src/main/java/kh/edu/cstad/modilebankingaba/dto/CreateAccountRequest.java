package kh.edu.cstad.modilebankingaba.dto;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String actNo,
        BigDecimal balance,
        Integer customerId,
        Integer accountTypeId
) {
}
