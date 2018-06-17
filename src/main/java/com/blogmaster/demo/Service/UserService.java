package com.blogmaster.demo.Service;


import com.blogmaster.demo.Databean.User;
import com.blogmaster.demo.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * find the user by its email
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    /**
     * save the user information in database
     */
    public User save(User user) {
        return userRepository.save(user);
    }
}
