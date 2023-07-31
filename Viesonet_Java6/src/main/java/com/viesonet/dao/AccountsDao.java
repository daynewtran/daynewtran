package com.viesonet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.viesonet.entity.Accounts;
public interface AccountsDao extends JpaRepository<Accounts, String> {

}
