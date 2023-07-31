package com.viesonet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viesonet.entity.Users;

public interface UsersDao extends JpaRepository<Users, String> {

}
