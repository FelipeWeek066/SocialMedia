package com.Graimy.SocialMedia.domains;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.enums.Role;
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
public class User implements UserDetails, Serializable{
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
	private Role role;
	private LocalDate date;
	@Setter(value = AccessLevel.NONE)
	private List<PersonDTO> friends = new ArrayList<>();
	@Setter(value = AccessLevel.NONE)
	private List<PersonDTO> following = new ArrayList<>();
	@Setter(value = AccessLevel.NONE)
	private List<PersonDTO> followers = new ArrayList<>();
	@Setter(value = AccessLevel.NONE)
	private List<PersonDTO> FriendRequesting = new ArrayList<>();
	@Setter(value = AccessLevel.NONE)
	private List<PersonDTO> FriendRequested = new ArrayList<>();
	
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
