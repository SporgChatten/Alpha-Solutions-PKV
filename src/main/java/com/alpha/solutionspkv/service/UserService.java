package com.alpha.solutionspkv.service;

import com.alpha.solutionspkv.model.User;
import com.alpha.solutionspkv.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}