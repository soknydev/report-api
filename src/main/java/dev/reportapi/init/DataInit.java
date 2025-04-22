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
//    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        initAuthoritiesAndRoles();
    }

    private void initAuthoritiesAndRoles() {
        if (authorityRepository.count() == 0) {
            // Initialize authorities
            Authority userRead = new Authority();
            userRead.setName("user:read");

            Authority userWrite = new Authority();
            userWrite.setName("user:write");

            Authority productRead = new Authority();
            productRead.setName("product:read");

            Authority productWrite = new Authority();
            productWrite.setName("product:write");

            authorityRepository.saveAll(List.of(userRead, userWrite, productRead, productWrite));

            if (roleRepository.count() == 0 && userRepository.count() == 0) {

                // Create user role:user
                User user = new User();
                user.setUuid("123e4567-e89b-12d3-a456-426614174000");
                user.setName("User");
                user.setUsername("user");
                //user.setPassword(passwordEncoder.encode("user123"));
                user.setPassword("pas123");
                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);
                user.setCredentialsNonExpired(true);
                user.setEnabled(true);
                //user.setDeleted(false);
                //user.setBlocked(false);
                userRepository.save(user);

                // Create user role:admin
                User userAdmin = new User();
                userAdmin.setUuid("123e4567-e89b-12d3-a456-426614172030");
                userAdmin.setName("Admin");
                userAdmin.setUsername("admin");
                //userAdmin.setPassword(passwordEncoder.encode("admin123"));
                userAdmin.setPassword("pas123");
                userAdmin.setAccountNonExpired(true);
                userAdmin.setAccountNonLocked(true);
                userAdmin.setCredentialsNonExpired(true);
                userAdmin.setEnabled(true);
                //userAdmin.setDeleted(false);
                //userAdmin.setBlocked(false);
                userRepository.save(userAdmin);

                // Create roles and associate authorities
                Role userRole = new Role();
                userRole.setName("USER");
                userRole.setAuthorities(List.of(userRead, productRead));
                userRole.setUser(user);

                Role adminRole = new Role();
                adminRole.setName("ADMIN");
                adminRole.setAuthorities(List.of(userRead, userWrite, productRead, productWrite));
                adminRole.setUser(user);

                // Save roles
                roleRepository.saveAll(List.of(userRole, adminRole));

                // Assign roles to the user
                user.setRoles(List.of(userRole));
                userAdmin.setRoles(List.of(userRole, adminRole));
                userRepository.saveAll(List.of(user, userAdmin));
            }
        }
    }


}
