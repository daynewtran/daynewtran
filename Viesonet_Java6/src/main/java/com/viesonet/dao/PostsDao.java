package com.viesonet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viesonet.entity.Posts;

public interface PostsDao extends JpaRepository<Posts, Integer>{

}
