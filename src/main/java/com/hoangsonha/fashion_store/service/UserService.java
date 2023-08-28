package com.hoangsonha.fashion_store.service;

import com.hoangsonha.fashion_store.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void saveUser(User user);
    public boolean checkUser(String email, String password);

    public int checkRole(String email);

    public boolean checkUserByEmail(String email);

    public boolean checkUserByPassword(String email, String password);

    public User getUserByEmail(String email);
    public List<User> getAllUsers();

    public Optional<User> getUserById(Integer id);
    public String getLastName(String email);

    public String getFirstName(String email);

    public String getPhone(String email);

    public boolean checkEmailExist(String email);
}
