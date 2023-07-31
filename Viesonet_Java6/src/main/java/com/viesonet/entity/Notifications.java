package com.viesonet.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Notifications {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer notificationId;

	private String notificationContent;
	
	@ManyToOne
	@JoinColumn(name = "receiverId")
	private Users receiver;
	
	@ManyToOne
	@JoinColumn(name = "postId")
	private Posts post;
	
	@ManyToOne
	@JoinColumn(name = "notificationTypeId")
	private NotificationType notificationType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date notificationDate;

}
