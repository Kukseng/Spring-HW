package kh.edu.cstad.modilebankingaba.init;


import jakarta.annotation.PostConstruct;
import kh.edu.cstad.modilebankingaba.domain.Role;
import kh.edu.cstad.modilebankingaba.domain.User;
import kh.edu.cstad.modilebankingaba.repository.RoleRepository;
import kh.edu.cstad.modilebankingaba.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SecurityInitialize {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    void init() {

        if(userRepository.count() == 0) {
            Role roleUser = new Role();
            roleUser.setIsEnabled(true);
            roleUser.setName("USER");
            Role roleAdmin = new Role();
            roleAdmin.setName("ADMIN");
            roleAdmin .setIsEnabled(true);
           Role roleCustomer = new Role();
           roleCustomer.setName("STAFF");
            roleCustomer.setIsEnabled(true);

           roleRepository.saveAll(List.of(roleUser,roleAdmin,roleCustomer));

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("qwer123"));
            admin.setIsEnabled(true);
            admin.setRoles(Set.of(roleUser,roleAdmin));

            User staff = new User();
            staff.setUsername("Staff");
            staff.setPassword(passwordEncoder.encode("qwer123"));
            staff.setIsEnabled(true);
            staff.setRoles(Set.of(roleUser,roleCustomer));

            User customer = new User();
            customer.setUsername("Customer");
            customer.setPassword(passwordEncoder.encode("qwer123"));
            customer.setIsEnabled(true);
            customer.setRoles(Set.of(roleUser));

            userRepository.saveAll(List.of(admin,staff,customer));
        }

    }

}
