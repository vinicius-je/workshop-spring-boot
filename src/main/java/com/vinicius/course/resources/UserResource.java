package com.vinicius.course.resources;

import com.vinicius.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "vinicius", "vinicius@email.com", "90900909", "123");
        return ResponseEntity.ok().body(u);
    }
}
