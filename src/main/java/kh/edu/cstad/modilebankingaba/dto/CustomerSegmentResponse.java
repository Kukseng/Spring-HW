package kh.edu.cstad.modilebankingaba.dto;

public record CustomerSegmentResponse(
        Integer id,
        String segmentName,
        Boolean isDeleted
) {}