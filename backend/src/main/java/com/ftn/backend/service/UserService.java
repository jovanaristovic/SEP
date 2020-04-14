package com.ftn.backend.service;

import com.ftn.backend.dto.PurchaseDto;
import com.ftn.backend.dto.RegisterUserDto;
import com.ftn.backend.model.Purchase;
import com.ftn.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    User findUserByUsername(String username);
    User findUserByEmail(String email);
    List<User> findAll();
    User register (RegisterUserDto registerUserDto);
    User saveUser (User user);
    User findUserById(Long id);
    List<PurchaseDto> findPurchasesByUsername (String username);

}
