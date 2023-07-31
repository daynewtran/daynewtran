package com.viesonet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viesonet.entity.Comments;

public interface CommentsDao extends JpaRepository<Comments, Integer> {

}
