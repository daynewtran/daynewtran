package com.viesonet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viesonet.entity.Follow;

public interface FollowDao extends JpaRepository<Follow, Integer> {

}
