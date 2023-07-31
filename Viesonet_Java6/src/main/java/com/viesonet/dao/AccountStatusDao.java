package com.viesonet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viesonet.entity.AccountStatus;

public interface AccountStatusDao extends JpaRepository<AccountStatus, Integer> {

}
