//package kh.edu.cstad.modilebankingaba.security;
//
//import lombok.RequiredArgsConstructor;
//import org.aspectj.weaver.patterns.IToken;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
////    @Bean
////    public  InMemoryUserDetailsManager InMemoryUserDetailsManager(){
////
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////
//    ////        Create Admin
////
////        UserDetails admin = User.builder()
////
////                .username("admin")
////                .password("{noop}admin123")
////                .roles("ADMIN")
////                .build();
////        manager.createUser(admin);
////
////        UserDetails staff = User.builder()
////
////                .username("staff")
////                .password("{noop}staff123")
////                .roles("STAFF")
////                .build();
////        manager.createUser(staff);
////
////        return manager;
////
////
////    }
//
//    private final UserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
//
//
//
//
////        TODO
////      ALL request must be authenticated
//        http.authorizeHttpRequests(request
//                        -> request
////                .requestMatchers("/api/v1/customers/**")
////                .hasAnyRole("ADMIN", "STAFF")
////                .requestMatchers("api/v1/accounts/**")
////                .hasAnyRole("USER")
//                        .requestMatchers(HttpMethod.POST,"/api/v1/customers/**")
//                        .hasAnyRole("ADMIN", "STAFF")
//                        .anyRequest().authenticated()
//        );
//
////        Disable form default
//
//        http.formLogin(form -> form.disable());
//        http.csrf(token -> token.disable());
//
//    http.oauth2ResourceServer(OAuth2 ->
//            OAuth2.jwt(Customizer.withDefaults())
//            );
////      set security mechanism
//        http.httpBasic(Customizer.withDefaults());
//
////      set sessionManage with stateless
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//
//        return http.build();
//    }
//
//}