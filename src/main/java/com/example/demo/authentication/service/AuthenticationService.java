package com.example.demo.authentication.service;

import com.example.demo.authentication.dal.service.UserRolesDto;
import com.example.demo.authentication.dal.entity.RoleEntity;
import com.example.demo.authentication.dal.entity.TokenEntity;
import com.example.demo.authentication.dal.entity.UserEntity;
import com.example.demo.authentication.dal.repository.TokenRepository;
import com.example.demo.authentication.dal.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    private static final int TOKEN_VALIDITY_IN_MINUTES = 60;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public String authenticate(String username, String password) {
        UserEntity optionalUser = userRepository.findByUsername(username);

        if (optionalUser == null) {
            throw new AuthenticationCredentialsNotFoundException("Username and/or password do not match!");
        }

        if ( ! passwordEncoder.matches(password,
                optionalUser.getPasswordHash())) {
            throw new AuthenticationCredentialsNotFoundException("Username and/or password do not match!");
        }

        TokenEntity token = new TokenEntity();
        String randomString = UUID.randomUUID().toString();
        token.setToken(randomString);
        token.setUser(optionalUser);
        token.setValidUntil(LocalDateTime.now());

        tokenRepository.save(token);

        return token.getToken();
    }

    public UserRolesDto mapToUserRolesDto(UserEntity user, Set<String> roles){
        UserRolesDto userRolesDto = new UserRolesDto();

        userRolesDto.setId(user.getId());
        userRolesDto.setUsername(user.getUsername());
        userRolesDto.setRoles(roles);
        userRolesDto.setFirstName(user.getFirstName());
        userRolesDto.setLastName(user.getLastName());
        userRolesDto.setEmail(user.getEmail());
        userRolesDto.setPhone(user.getPhone());

        return userRolesDto;
    }

    @Transactional
    public UserRolesDto authenticate(String token) {
        System.out.println("qqqqqqqqqqqqqqqqqq3:" + token);
        Optional<TokenEntity> optionalToken = tokenRepository.findByToken(token);

        if (optionalToken.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }

        validateTokenExpiration(optionalToken.get());

        Collection<RoleEntity> roles = optionalToken.get().getUser().getRoles();
        Set<String> roleNames = roles.stream()
                .map( entry -> entry.getRoleName())
                .collect(Collectors.toSet());

        return mapToUserRolesDto(optionalToken.get().getUser(), roleNames);
    }

    private void validateTokenExpiration(TokenEntity token) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenExpiration = token.getValidUntil().plus(TOKEN_VALIDITY_IN_MINUTES, ChronoUnit.MINUTES);

        if ( now.isAfter(tokenExpiration) ) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed!");
        }
    }

    @Transactional
    public void tokenRemove(String token) {
        tokenRepository.deleteByToken(token);
    }

}
