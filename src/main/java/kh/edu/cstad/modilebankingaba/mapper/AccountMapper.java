package kh.edu.cstad.modilebankingaba.mapper;

import kh.edu.cstad.modilebankingaba.domain.Account;
import kh.edu.cstad.modilebankingaba.dto.CreateAccountRequest;
import kh.edu.cstad.modilebankingaba.dto.ResponseAccount;
import kh.edu.cstad.modilebankingaba.dto.UpdateAccount;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    @Mapping(target = "accountType", ignore = true)
    void toAccountPartially(UpdateAccount updateAccount, @MappingTarget Account account);


    @Mapping(target = "accountType", ignore = true)
    Account fromCreateRequest(CreateAccountRequest request);

    @Mapping(target = "accountType", ignore = true)
    ResponseAccount toResponse(Account account);
}
