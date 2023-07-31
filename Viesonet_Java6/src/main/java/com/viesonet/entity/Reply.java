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
@Data
@Table(name = "Reply")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int replyId;

	private String replyContent;
	
	@ManyToOne
	@JoinColumn(name = "commentId" )
	private Comments comment;
	
	@ManyToOne
	@JoinColumn(name = "userId" )
	private Users user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyDate;
	
}
