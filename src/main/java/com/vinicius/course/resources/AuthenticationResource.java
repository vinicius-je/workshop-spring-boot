package com.vinicius.course.resources;
import com.vinicius.course.entities.User;
import com.vinicius.course.entities.dto.*;
import com.vinicius.course.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value =  "/auth")
@CrossOrigin("*")
public class AuthenticationResource {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public User registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getName(), body.getEmail(), body.getPhone(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.login(body.getEmail(), body.getPassword());
    }

}
