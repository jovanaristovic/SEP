package com.ftn.backend.service.serviceImpl;

import com.ftn.backend.dto.RegisterUserDto;
import com.ftn.backend.model.Authority;
import com.ftn.backend.model.User;
import com.ftn.backend.repository.AuthorityRepository;
import com.ftn.backend.repository.UserRepository;
import com.ftn.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
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


}
