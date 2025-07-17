package kh.edu.cstad.modilebankingaba.init;


import jakarta.annotation.PostConstruct;
import kh.edu.cstad.modilebankingaba.domain.CustomerSegment;
import kh.edu.cstad.modilebankingaba.repository.CustomerSegmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerSegmentInitialize {

    private final CustomerSegmentRepository customerSegmentRepository;

    @PostConstruct
    public void initializeCustomerSegment() {
        if (customerSegmentRepository.count() == 0) {
            CustomerSegment regular = new CustomerSegment();
            regular.setSegmentName("REGULAR");
            regular.setDescription("Regular Segment");
            regular.setIsDeleted(false);

            CustomerSegment silver = new CustomerSegment();
            silver.setSegmentName("Silver");
            silver.setDescription("Silver  Segment");
            silver.setIsDeleted(false);

            CustomerSegment gold = new CustomerSegment();
            gold.setSegmentName("GOld");
            gold.setDescription("Gold  Segment");
            gold.setIsDeleted(false);

            customerSegmentRepository.saveAll(List.of(regular, silver, gold));
        }

    }

}
