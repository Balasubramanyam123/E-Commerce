package org.niit.userauthenticationapp.controller;

import org.niit.userauthenticationapp.model.User;
import org.niit.userauthenticationapp.service.JwtTokenGenerator;
import org.niit.userauthenticationapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    UserService userService;
    JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    public UserController(UserService userService, JwtTokenGenerator jwtTokenGenerator) {
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }



    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.FOUND);
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        return new ResponseEntity<>(userService.deleteUserByEmail(email), HttpStatus.OK);
    }
    @PutMapping("/user/{email}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String email) {
        return new ResponseEntity<>(userService.updateUser(user,email), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user) {
        User result = userService.loginCheck(user.getEmail(), user.getPassword());
        if (result != null) {
            result.setPassword("");

            Map<String, String> map = jwtTokenGenerator.generateJwt(result);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {

            return new ResponseEntity<>("invalid user", HttpStatus.NOT_FOUND);
        }
    }
}
