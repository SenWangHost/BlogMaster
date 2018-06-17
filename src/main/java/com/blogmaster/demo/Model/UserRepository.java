package com.blogmaster.demo.Model;

import com.blogmaster.demo.Databean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * find the user by email
     */
    User findByEmail(String email);
}
