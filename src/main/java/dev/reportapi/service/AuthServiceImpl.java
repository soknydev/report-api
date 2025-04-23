package dev.reportapi.service;


import dev.reportapi.dto.AuthResponse;
import dev.reportapi.dto.LoginRequest;
import dev.reportapi.dto.RefreshTokenRequest;
import dev.reportapi.dto.RegisterRequest;
import dev.reportapi.model.Role;
import dev.reportapi.model.User;
import dev.reportapi.repository.RoleRepository;
import dev.reportapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final TokenService tokenService;

    @Override
    public void register(RegisterRequest registerRequest) {
        // Check if the user already exists
        if (userRepository.existsByUsername(registerRequest.username())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User already exists!"
            );
        }

        // Fetch the role with id=1
        Role role = roleRepository.findById(1L)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Role with id=1 not found!"
                        ));

        // Create a new user entity
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setName(registerRequest.name());
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setRoles(List.of(role));

        // Save the user
        userRepository.save(user);

    }

    @Override
    public AuthResponse refresh(RefreshTokenRequest refreshTokenRequest) {
        Authentication auth = new BearerTokenAuthenticationToken(
                refreshTokenRequest.refreshToken()
        );

        auth = jwtAuthenticationProvider.authenticate(auth);
        return tokenService.createToken(auth);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password());

        auth = daoAuthenticationProvider.authenticate(auth);
            return tokenService.createToken(auth);
    }

}
