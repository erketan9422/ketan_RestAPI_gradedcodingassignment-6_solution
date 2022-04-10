package com.gl.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.employee.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
}
