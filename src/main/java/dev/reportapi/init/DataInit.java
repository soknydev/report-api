package dev.reportapi.init;

import dev.reportapi.model.Authority;
import dev.reportapi.model.Role;
import dev.reportapi.model.User;
import dev.reportapi.repository.AuthorityRepository;
import dev.reportapi.repository.RoleRepository;
import dev.reportapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        initAuthoritiesAndRoles();
    }

    private void initAuthoritiesAndRoles() {
        if (authorityRepository.count() == 0) {
            // Create authorities
            Authority userRead = new Authority(); userRead.setName("user:read");
            Authority userWrite = new Authority(); userWrite.setName("user:write");
            Authority customerRead = new Authority(); customerRead.setName("customer:read");
            Authority customerWrite = new Authority(); customerWrite.setName("customer:write");
            Authority reportRead = new Authority(); reportRead.setName("report:read");
            Authority reportWrite = new Authority(); reportWrite.setName("report:write");

            authorityRepository.saveAll(List.of(
                    userRead, userWrite,
                    customerRead, customerWrite,
                    reportRead, reportWrite
            ));

            if (roleRepository.count() == 0 && userRepository.count() == 0) {

                // Create roles and associate authorities
                Role userRole = new Role();
                userRole.setName("USER");
                userRole.setAuthorities(List.of(userRead, customerRead, customerWrite, reportRead));

                Role adminRole = new Role();
                adminRole.setName("ADMIN");
                adminRole.setAuthorities(List.of(userRead, userWrite, customerRead, customerWrite, reportRead, reportWrite));

                roleRepository.saveAll(List.of(userRole, adminRole));

                // Create user with role USER
                User user = new User();
                user.setUuid("123e4567-e89b-12d3-a456-426614174000");
                user.setName("User");
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);
                user.setCredentialsNonExpired(true);
                user.setEnabled(true);
                user.setRole(userRole);

                // Create admin with role ADMIN
                User userAdmin = new User();
                userAdmin.setUuid("123e4567-e89b-12d3-a456-426614172030");
                userAdmin.setName("Admin");
                userAdmin.setUsername("admin");
                userAdmin.setPassword(passwordEncoder.encode("admin123"));
                userAdmin.setAccountNonExpired(true);
                userAdmin.setAccountNonLocked(true);
                userAdmin.setCredentialsNonExpired(true);
                userAdmin.setEnabled(true);
                userAdmin.setRole(adminRole);

                userRepository.saveAll(List.of(user, userAdmin));
            }
        }
    }
}
