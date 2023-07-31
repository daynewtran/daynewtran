package com.viesonet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viesonet.entity.Violations;

public interface ViolationsDao extends JpaRepository<Violations, Integer> {

}
