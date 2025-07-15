package kh.edu.cstad.modilebankingaba.repository;

import kh.edu.cstad.modilebankingaba.domain.CustomerSegment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSegmentRepository extends JpaRepository<CustomerSegment, Integer> {
    boolean existsBySegmentName(String segmentName);
}