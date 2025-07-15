package kh.edu.cstad.modilebankingaba.dto;

import lombok.Builder;

@Builder
public record ResponseCustomer(
        String userName,

        String gender,
        String email,
        String phoneNumber,
        String nationalCardId

) {
}
