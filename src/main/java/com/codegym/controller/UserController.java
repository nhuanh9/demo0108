package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("")
    public ResponseEntity<Iterable<User>> findAll() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> save(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        userService.save(user);
        return new ResponseEntity(userService.findById(user.getId()).get(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(user.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> edit(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userCurent = userService.findById(id);
        if (!userCurent.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        if (user.getFullName().equals("")) {
            user.setFullName(userCurent.get().getFullName());
        }
        if (user.getPassword().equals("")) {
            user.setPassword(userCurent.get().getPassword());
        }
        if (user.getUsername().equals("")) {
            user.setUsername(userCurent.get().getUsername());
        }
        user.setId(id);
        userService.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public ResponseEntity<User> search(@RequestParam String username) {
//        Optional<User> user = userService.findByUsername(username);
//        if (!user.isPresent()) {
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity(user.get(), HttpStatus.OK);
//    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<User>> searchName(@RequestParam String name) {
        Iterable<User> users = userService.findAllByFullNameContaining(name);
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
