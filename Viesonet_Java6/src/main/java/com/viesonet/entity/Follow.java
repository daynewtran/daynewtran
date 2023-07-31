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
@Table(name = "Follow")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int followId;

	@ManyToOne
	@JoinColumn(name = "followerId")
	private Users follower;
	@ManyToOne
	@JoinColumn(name = "followingId")
	private Users following;

	@Temporal(TemporalType.DATE)
	private Date followDate;

}
