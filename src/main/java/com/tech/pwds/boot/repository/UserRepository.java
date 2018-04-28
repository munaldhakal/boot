package com.tech.pwds.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.pwds.boot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

	User findByUsernameOrPhoneNumber(String username, String phoneNumber);

	User findByPhoneNumber(String phoneNumber);

}
