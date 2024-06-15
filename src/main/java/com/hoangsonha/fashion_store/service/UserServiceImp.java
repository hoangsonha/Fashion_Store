package com.hoangsonha.fashion_store.service;

import com.hoangsonha.fashion_store.entity.User;
import com.hoangsonha.fashion_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired private UserRepository userRepository;
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean checkUser(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if(user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public int checkRole(String email) {
        User user = userRepository.getUserByEmail(email);
        return user.getRole();
    }

    @Override
    public boolean checkUserByEmail(String email) {
        boolean exist = userRepository.existsByEmail(email);
        if(!exist) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUserByPassword(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if(user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public String getLastName(String email) {
        User user = userRepository.getUserByEmail(email);
        return user.getLastName();
    }

    @Override
    public String getFirstName(String email) {
        User user = userRepository.getUserByEmail(email);
        return user.getFirstName();
    }

    @Override
    public String getPhone(String email) {
        User user = userRepository.getUserByEmail(email);
        return user.getPhoneNumber();
    }

    @Override
    public boolean checkEmailExist(String email) {
        String email_user = email.trim().toLowerCase();
        boolean exist = userRepository.existsByEmail(email_user);
        if(exist) {
            return true;
        }
        return false;
    }
}
