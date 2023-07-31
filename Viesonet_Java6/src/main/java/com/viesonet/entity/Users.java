package com.viesonet.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {
	@Id
	private String userId;
	private String username;
	private String address;
	private String relationship;
	private String introduction;
	private boolean gender;
	private Date birthday;
	private String avatar;
	private String background;
	private int violationCount;
	private Date createDate;

	@OneToOne(mappedBy = "user")
	private Accounts account;

	@OneToMany(mappedBy = "user")
	private List<Posts> posts;

	@OneToMany(mappedBy = "user")
	private List<Reply> reply;

	@OneToMany(mappedBy = "user")
	private List<Favorites> favorites;

	@OneToMany(mappedBy = "user")
	private List<Comments> comments;

	@OneToMany(mappedBy = "receiver")
	private List<Notifications> notifications;

	@OneToMany(mappedBy = "follower")
	private List<Follow> followers;

	@OneToMany(mappedBy = "following")
	private List<Follow> followings;
	
	@OneToMany(mappedBy = "user")
	private List<Violations> violations;

}
