// package com.service.medicine.configuration;
//
// import com.service.medicine.enums.Roles;
// import com.service.medicine.model.Role;
// import com.service.medicine.model.User;
// import com.service.medicine.reponsitory.UserRepository;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.password.PasswordEncoder;
//
// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
//
// @Configuration
// @Slf4j
//// trong csdl phải có role : ADMIN mới khởi tạo được!!!
// public class ApplicationInitCofig {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Bean
//    @ConditionalOnProperty(prefix = "spring",
//            value = "datasource.driverClassName",
//            havingValue ="com.mysql.cj.jdbc.Driver"
//    )//điều kiện
//        // chỉ khởi tạo khi có start csdl mysql
//    ApplicationRunner applicationRunner(UserRepository userRepository){
//        return args -> {
//            if(userRepository.findByUsername("admin").isEmpty()){
//                var roles = new HashSet<Role>();
//                roles.add(Role.builder()
//                                .name(Roles.ADMIN.name())
//                                .description("admin")
//                        .build());
//                log.info(Roles.ADMIN.name());
//                User user = User.builder()
//                        .username("admin")
//                        .password(passwordEncoder.encode("admin"))
//                        .roles(roles)
//                        .build();
//
//                userRepository.save(user);
//                log.warn("admin đăng nhập tên admin, mk admin");
//
//            }
//
//        };
//    }
//
// }
