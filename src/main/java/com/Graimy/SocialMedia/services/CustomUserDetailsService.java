package com.Graimy.SocialMedia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.repository.UserRepository;
import com.Graimy.SocialMedia.services.exception.ObjectNotFoundException;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String userName) {
		Optional<User> user = repository.findByName(userName);
		if(user.isPresent()) {
			return user.get();
		}else {
			throw new ObjectNotFoundException("User not found with username: " + userName);
		}
	}
	
}
