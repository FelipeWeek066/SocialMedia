package com.Graimy.SocialMedia.domains.DTO;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.Graimy.SocialMedia.domains.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;
	private String email;
	
	public UserDTO(User obj) {
		setId(obj.getId());
		setName(obj.getName());
		setEmail(obj.getEmail());
	}
}
