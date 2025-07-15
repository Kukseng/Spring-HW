package kh.edu.cstad.modilebankingaba.mapper;


import kh.edu.cstad.modilebankingaba.domain.Customer;
import kh.edu.cstad.modilebankingaba.dto.CreateCustomerRequest;
import kh.edu.cstad.modilebankingaba.dto.ResponseCustomer;
import kh.edu.cstad.modilebankingaba.dto.UpdateCustomer;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomer(
            UpdateCustomer updateCustomer,
            @MappingTarget Customer customer
    );

    ResponseCustomer mapResponseCustomer(Customer customer);

    @Mapping(target = "customerSegment", ignore = true)
    Customer frmCreateCustomer(CreateCustomerRequest request);
}
