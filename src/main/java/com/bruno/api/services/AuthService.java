package com.bruno.api.services;


import com.bruno.api.dto.AccountCredentialsDto;
import com.bruno.api.dto.TokenDto;
import com.bruno.api.repository.UserRepository;
import com.bruno.api.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    UserRepository userRepository;

    public ResponseEntity signIn(AccountCredentialsDto dto) {
        try {
            String userName = dto.getUsername();
            String password = dto.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
            System.out.println("verificando se passa aqui");
            var byUSerName = userRepository.findByUSerName(userName);
            var response = new TokenDto();
            if (byUSerName != null) {
                response = tokenProvider.createAccessToken(userName, byUSerName.getRoles());
            } else {
                throw new UsernameNotFoundException("username not found");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/passwords");
        }
    }
}
