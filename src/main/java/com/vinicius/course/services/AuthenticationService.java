package com.vinicius.course.services;

import com.vinicius.course.entities.Role;
import com.vinicius.course.entities.User;
import com.vinicius.course.entities.dto.LoginResponseDTO;
import com.vinicius.course.repositories.RoleRepository;
import com.vinicius.course.repositories.UserRepository;
import com.vinicius.course.services.jwt.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public User registerUser(String name, String email, String phone, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("CLIENT").get();

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        //User user = new User(0, username, encodedPassword, roles);
        User user = new User(0L, name, email, phone, encodedPassword, roles);

        return userRepository.save(user);
    }

    public LoginResponseDTO login(String email, String password){

        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication auth = authenticationManager.authenticate(authToken);

            String token = tokenService.generateJwt(auth);

            User user = userRepository.findByEmail(email).get();
            return new LoginResponseDTO(user, token);
        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");
        }
    }
}
