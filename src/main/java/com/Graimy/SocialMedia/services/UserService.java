package com.Graimy.SocialMedia.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.enums.Role;
import com.Graimy.SocialMedia.repository.UserRepository;
import com.Graimy.SocialMedia.services.exception.BlankContentException;
import com.Graimy.SocialMedia.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<User> searchName(String name){
		return repository.searchName(name);
	}
	
	public User findByName(String name){
		Optional<User> obj = repository.findByName(name);
		return obj.orElseThrow(() -> new ObjectNotFoundException(name));
	}
	
	public void unFollowSomeOne(String follower, String followed) {
		User tUser = findByName(follower);
		User fTUser = findByName(followed);
		if(tUser.getFollowing().contains(new PersonDTO(fTUser))) {
			tUser.getFollowing().remove(new PersonDTO(fTUser));
			fTUser.getFollowers().remove(new PersonDTO(tUser));
			if(tUser.getFriends().contains(new PersonDTO(fTUser))) {
				tUser.getFriends().remove(new PersonDTO(fTUser));
				fTUser.getFriends().remove(new PersonDTO(tUser));
			}
			repository.saveAll(Arrays.asList(tUser, fTUser));
		}else {
			throw new ObjectNotFoundException(tUser.getName() + " does not follow: " + fTUser.getName());
		}
	}
	
	public void followSomeOne(String follower, String followed) {
		User tUser = findByName(follower);
		User fTUser = findByName(followed);
		if(!tUser.getFollowing().contains(new PersonDTO(fTUser))) {
			tUser.getFollowing().add(new PersonDTO(fTUser));
			fTUser.getFollowers().add(new PersonDTO(tUser));
			if(tUser.getFollowing().contains(new PersonDTO(fTUser))) {
				tUser.getFriends().add(new PersonDTO(fTUser));
				fTUser.getFriends().add(new PersonDTO(tUser));
				System.out.println(tUser.getName() + " is now friend of: " + fTUser.getName());
			}
		}
		repository.saveAll(Arrays.asList(tUser, fTUser));
	}
	
	public void insert(User user) {
		user.setDate(LocalDate.now());
		user.setRole(Role.user);
		if(!user.getPassword().isBlank()) {
			if(user.getPassword().length() >= 6) {
				String encodedPassword = passwordEncoder.encode(user.getPassword());
				if(!user.getEmail().isBlank()) {
					user.setPassword(encodedPassword);
					repository.save(user);
					return;
				}
			}
		}
	}
}
