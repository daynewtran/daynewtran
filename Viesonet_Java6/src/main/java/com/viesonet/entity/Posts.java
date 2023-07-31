package com.viesonet.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate ;

	private String content;
	
	private Boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Users user;

	private Integer likeCount;

	private Integer commentCount;
	
	@OneToMany(mappedBy = "post")
	List<Comments> comments;
	
	@OneToMany(mappedBy = "post")
	List<Favorites> favorites;
	
	@OneToMany(mappedBy = "post")
	List<Notifications> notifications;
	
	@OneToMany(mappedBy = "post")
	private List<Violations> violations;
	
	@OneToMany(mappedBy = "post")
	private List<Images> images;
}
