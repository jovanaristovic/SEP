package com.ftn.backend.service.serviceImpl;

import com.ftn.backend.dto.PurchaseDto;
import com.ftn.backend.dto.RegisterUserDto;
import com.ftn.backend.model.*;
import com.ftn.backend.repository.AuthorityRepository;
import com.ftn.backend.repository.UserRepository;
import com.ftn.backend.service.JournalService;
import com.ftn.backend.service.UserService;
import com.ftn.backend.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JournalService journalService;

    @Autowired
    private WorkService workService;


    public User getUserById(Long id) {
        User user =  this.userRepository.findUserById(id);
        return user;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        User u = userRepository.findUserByUsername(username);
        return u;
    }
    @Override
    public User findUserByEmail(String email) {
        User u = userRepository.findUserByEmail(email);
        return u;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User register(RegisterUserDto registerUserDto) {


            boolean exist = false;
            List<User> allUsers = this.userRepository.findAll();
            for(User u: allUsers){
                if(u.getEmail().equals(registerUserDto.getEmail())){
                    exist = true;
                }
            }
            if(exist == true){
                return null;
            }else {
                User user = new User();
                user.setName(registerUserDto.getName());
                user.setLastName(registerUserDto.getLastName());
                user.setEnabled(true);
                user.setCity(registerUserDto.getCity());
                user.setEmail(registerUserDto.getEmail());
                user.setUsername(registerUserDto.getEmail());
                user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
                Authority authority = this.authorityRepository.findAuthorityByName("ROLE_USER");
                List<Authority> authorities = new ArrayList<>();
                authorities.add(authority);
                user.setAuthorities(authorities);

                return this.userRepository.save(user);
            }

    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return this.userRepository.findUserById(id);
    }

    @Override
    public List<PurchaseDto> findPurchasesByUsername(String username) {
        User user = this.userRepository.findUserByUsername(username);
        List<Purchase> purchases = user.getPurchases();

        List<PurchaseDto> purchaseDtos = new ArrayList<>();
        Journal journal = null;
        Work work = null;
        for(Purchase p: purchases){

            if(p.getTypeOfProduct().equals("Magazine")) {
                journal = this.journalService.findJournalById(p.getId());
                PurchaseDto purchaseDto = new PurchaseDto(p.getStatus(), p.getTypeOfProduct(),journal.getTitle(), journal.getPrice());
                purchaseDtos.add(purchaseDto);

            } else {
                work = this.workService.findById(p.getId());
                PurchaseDto purchaseDto = new PurchaseDto(p.getStatus(), p.getTypeOfProduct(),work.getTitle(), work.getPrice());
                purchaseDtos.add(purchaseDto);

            }


        }
        return purchaseDtos;
    }


}
