package com.example.demo.authentication.controller;

import com.example.demo.authentication.service.AuthenticationService;
import com.example.demo.authentication.dal.service.UserRolesDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    private final String AUTHORIZATION_HEADER = "Authorization";
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/api/authentication")
    public UserRolesDto login(@RequestHeader(value = AUTHORIZATION_HEADER, required = false) Optional<String> authentication,
                      HttpServletResponse response) {
        if (authentication.isEmpty()) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }

        String[] credentials = credentialsDecode(authentication.get());

        String token = authenticationService.authenticate(credentials[0], credentials[1]);

        response.setStatus(HttpStatus.OK.value());
        System.out.println("qqqqqqqqqqqqqqqqqq1: " + token);
        response.addHeader(AUTHORIZATION_HEADER, "Bearer " + token);
        System.out.println("qqqqqqqqqqqqqqqqqq2: " + Arrays.toString(response.getHeaderNames().toArray()));
        UserRolesDto ret = authenticationService.authenticate(token);
        System.out.println("qqqqqqqqqqqqqqqqqq4: " + ret);
        return ret;
    }

    private static String[] credentialsDecode(String authorization) {
        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);

        return  credentials.split(":", 2);
    }

    @GetMapping("/api/authentication/{token}")
    public UserRolesDto getRoles(@PathVariable String token){
       return this.authenticationService.authenticate(token);
    }

    @DeleteMapping("/api/authentication")
    public void logoff(@RequestHeader(value = AUTHORIZATION_HEADER, required = true) Optional<String> authentication) {
        String token = authentication.get().substring("Bearer".length()).trim();
        authenticationService.tokenRemove(token);
    }



}
