package com.richa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richa.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
	boolean existsByEmail(String email);

}
