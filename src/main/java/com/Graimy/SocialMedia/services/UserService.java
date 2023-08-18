package com.Graimy.SocialMedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Graimy.SocialMedia.domains.Post;
import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.repository.UserRepository;
import com.Graimy.SocialMedia.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
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
	
	public User addPostIn(Post post) {
		User obj = findById(post.getPersonDTO().getId());
		obj.getPosts().add(post);
		repository.save(obj);
		return obj;
	}
	
	
}
