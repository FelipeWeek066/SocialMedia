package com.Graimy.SocialMedia.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.enums.Role;
import com.Graimy.SocialMedia.repository.UserRepository;
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
		}
		repository.saveAll(Arrays.asList(tUser, fTUser));
	}
	
	public void requestFriend(String follower, String followed) {
		User u1 = findByName(follower);
		User u2 = findByName(followed);
		
		if(!u1.getFriends().contains(new PersonDTO(u2))) {
			if(!u1.getFriendRequesting().contains(new PersonDTO(u2))) {
				if(!u2.getFriendRequesting().contains(new PersonDTO(u1))) {
					u1.getFriendRequesting().add(new PersonDTO(u2));
					u2.getFriendRequested().add(new PersonDTO(u1));
				}else {
					u2.getFriendRequesting().remove(new PersonDTO(u1));
					u1.getFriendRequested().remove(new PersonDTO(u2));
					u1.getFriends().add(new PersonDTO(u2));
					u2.getFriends().add(new PersonDTO(u1));
				}
			}else {
				u1.getFriendRequesting().remove(new PersonDTO(u2));
				u2.getFriendRequested().remove(new PersonDTO(u1));
			}
		}
		
		repository.saveAll(Arrays.asList(u1,u2));
	}
	
	public void removeRelationShip(String follower, String followed) {
		User u1 = findByName(follower);
		User u2 = findByName(followed);
		
		if(u1.getFriends().contains(new PersonDTO(u2))) {
			u1.getFriends().remove(u2);
			u2.getFriends().remove(u1);
			
			repository.saveAll(Arrays.asList(u1,u2));
		}
		
	}
	public List<PersonDTO> findFriends(String userName){
		return findByName(userName).getFriends();
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
