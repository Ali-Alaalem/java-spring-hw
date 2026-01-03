package com.todoApp.repositorys;

import com.todoApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String userEmailAddress);
    User findUserByEmail(String userEmailAddress);
}
