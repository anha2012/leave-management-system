package com.bugfearless.leave_management_system.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bugfearless.leave_management_system.dto.BaseResponse;
import com.bugfearless.leave_management_system.dto.LoginRequest;
import com.bugfearless.leave_management_system.dto.TokenDTO;
import com.bugfearless.leave_management_system.dto.UserDTO;
import com.bugfearless.leave_management_system.model.User;
import com.bugfearless.leave_management_system.repository.UserRepository;
import com.bugfearless.leave_management_system.utils.enums.StatusCode;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public BaseResponse<?> login(LoginRequest request) {
        BaseResponse<TokenDTO> response = new BaseResponse<>();
        Optional<User> userOptional = this.userRepository.findByUsername(request.getUsername());
        if (userOptional.isEmpty()) {
            return new BaseResponse<>(StatusCode.INVALID_CREDENTIALS);
        
        }
    
        User user = userOptional.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new BaseResponse<>(StatusCode.INVALID_CREDENTIALS);
        }
    
        String token = generateToken(user);
        response.setData(new TokenDTO(token));
        return response;
    }

    public BaseResponse<?> createUser(UserDTO userDTO) {
        BaseResponse<?> response = new BaseResponse<>();
        User user = new User();
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            return new BaseResponse<>(StatusCode.USERNAME_ALREADY_EXISTS);
        }
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        userRepository.save(user);
        return response;
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(8, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", user.getRole())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jWSObject = new JWSObject(header, payload);

        try {
            jWSObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jWSObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

}
