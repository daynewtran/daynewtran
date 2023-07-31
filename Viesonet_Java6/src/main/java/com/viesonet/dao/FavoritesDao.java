package com.viesonet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viesonet.entity.Favorites;

public interface FavoritesDao extends JpaRepository<Favorites, Integer> {

}
