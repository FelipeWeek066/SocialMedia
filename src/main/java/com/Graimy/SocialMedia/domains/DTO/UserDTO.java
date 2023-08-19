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
	private String email;
	
	public UserDTO(User obj) {
		setName(obj.getName());
		setEmail(obj.getEmail());
	}
}
