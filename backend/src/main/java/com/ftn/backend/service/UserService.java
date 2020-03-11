package com.ftn.backend.service;

import com.ftn.backend.dto.RegisterUserDto;
import com.ftn.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    User findUserByUsername(String username);
    User findUserByEmail(String email);
    List<User> findAll();
    User register (RegisterUserDto registerUserDto);

}
