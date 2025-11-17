package com.alpha.solutionspkv.Service;

import com.alpha.solutionspkv.Model.User;
import com.alpha.solutionspkv.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public void create(User user) { userRepository.create(user); }

    public void update(User user) { userRepository.update(user); }

    public void delete(int userId) { userRepository.delete(userId); }
}
