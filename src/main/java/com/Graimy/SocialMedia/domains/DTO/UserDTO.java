package com.Graimy.SocialMedia.domains.DTO;

import java.io.Serializable;

import com.Graimy.SocialMedia.domains.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int friends;
	private int followers;
	private int following;
	
	public UserDTO(User obj) {
		setName(obj.getName());
		setFriends(obj.getFriends().size());
		setFollowers(obj.getFollowers().size());
		setFollowing(obj.getFollowing().size());
	}
}
