package com.viesonet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viesonet.entity.Notifications;

public interface NotificationsDao extends JpaRepository<Notifications, Integer> {

}
