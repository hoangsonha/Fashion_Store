package com.hoangsonha.fashion_store.repository;

import com.hoangsonha.fashion_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByEmail(String email);

    public User findUserByPassword(String password);

    public int getUserByRole(int role);
    public User getUserByEmail(String email);

    public User getUserByPassword(String password);
    public boolean existsByEmail(String email);
    public boolean existsByPassword(String password);

    public User findUserByLastName(String lastName);

    public User getUserById(Integer id);


}
