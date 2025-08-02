package kh.edu.cstad.modilebankingaba.dto;


import lombok.Builder;

@Builder
public record ResponseMedia(
        String name,
        String extension,
        String mineMediaType,
        String uri,
        Long size
) {
}
