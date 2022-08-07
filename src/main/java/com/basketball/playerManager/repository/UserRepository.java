package com.basketball.playerManager.repository;

import com.basketball.playerManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserName(String userName);
    User findUserByMail(String mail);
}
