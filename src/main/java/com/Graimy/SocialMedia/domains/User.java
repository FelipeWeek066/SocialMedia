package com.Graimy.SocialMedia.domains;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Indexed(unique = true)
	private String name;
	@Indexed(unique = true)
	private String email;
	@Indexed(unique = true)
	private String password;
}
