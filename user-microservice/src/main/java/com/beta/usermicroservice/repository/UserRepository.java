package com.beta.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beta.usermicroservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
