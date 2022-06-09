package com.example.javasv2advprojectszalaytamas.controller;

import com.example.javasv2advprojectszalaytamas.dto.UserDto;
import com.example.javasv2advprojectszalaytamas.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private UserDetailsService userDetailsService;

    @GetMapping
    List<UserDto> findAllUser() {
        return userDetailsService.findAllUser();
    }
}
