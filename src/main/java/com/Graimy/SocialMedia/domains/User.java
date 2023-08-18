package com.Graimy.SocialMedia.domains;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Indexed(unique = true)
	@NonNull
	private String name;
	@Indexed(unique = true)
	@NonNull
	private String email;
	@Indexed(unique = true)
	@NonNull
	private String password;
	@NonNull
	private LocalDate date;
	@Setter(value = AccessLevel.NONE)
	@DBRef(lazy = true)
	private List<Post> posts = new ArrayList<>();
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
