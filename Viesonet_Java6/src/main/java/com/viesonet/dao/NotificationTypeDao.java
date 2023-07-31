package com.viesonet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viesonet.entity.NotificationType;

public interface NotificationTypeDao extends JpaRepository<NotificationType, Integer> {

}
