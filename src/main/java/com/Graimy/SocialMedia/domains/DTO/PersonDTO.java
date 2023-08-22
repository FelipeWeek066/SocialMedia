package com.Graimy.SocialMedia.domains.DTO;

import java.io.Serializable;

import com.Graimy.SocialMedia.domains.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public PersonDTO(User obj) {
		setName(obj.getName());
	}
}
