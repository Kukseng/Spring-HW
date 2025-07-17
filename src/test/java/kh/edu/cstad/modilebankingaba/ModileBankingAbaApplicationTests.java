package kh.edu.cstad.modilebankingaba;

import kh.edu.cstad.modilebankingaba.repository.CustomerSegmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModileBankingAbaApplicationTests {

    @Autowired
    private CustomerSegmentRepository customerSegmentRepository;

    @Test
    void Fetch() {
        customerSegmentRepository.findAll()
                .forEach(customerSegment -> System.out.println(customerSegment.getCustomers()));
    }

}
