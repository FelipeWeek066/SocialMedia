package com.Graimy.SocialMedia.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Graimy.SocialMedia.domains.Comment;
import com.Graimy.SocialMedia.domains.Post;
import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.repository.UserRepository;
import com.Graimy.SocialMedia.services.exception.BlankContentException;
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
		User obj = findByName(post.getAuthorDTO().getName());
		obj.getPosts().add(post);
		return repository.save(obj);
	}
	
	public User insert(User user) {
		user.setDate(LocalDate.now());
		if(user.getPassword().length() >= 8) {
			if(!user.getEmail().isBlank()) {
				return repository.save(user);
			}
		}
		throw new BlankContentException("post has blank content");
	}
	
	public User addCommentIn(Comment post) {
		User obj = findByName(post.getAuthor().getName());
		obj.getComments().add(post);
		repository.save(obj);
		return obj;
	}
	
	
}
