package com.abhi.demo.dao;

import com.abhi.demo.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDAO {

    // CREATE
    User save(User user);

    // READ
    Optional<User> findById(Long id);
    List<User> findAll();
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);

    // UPDATE
    User update(User user);

    // DELETE
    void deleteById(Long id);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

    // CUSTOM QUERIES
    Optional<User> findByPhoneNumberStartingWith(String prefix);
    long count();
}

