package com.Graimy.SocialMedia.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Graimy.SocialMedia.domains.Post;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.domains.DTO.PostDTO;
import com.Graimy.SocialMedia.repository.PostRepository;
import com.Graimy.SocialMedia.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;
	@Autowired
	private UserService userService;
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
	
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<Post> findAll(){
		List<Post> list = repository.findAll();
		return list;
	}
	
	public List<Post> findByUser(PersonDTO dto){
		return repository.findByPersonDTO(dto);
	}
	
	public Post insert(PostDTO post) {
		Post obj = new Post(post.getId(),post.getContent(),Instant.now(),post.getPersonDTO());
		if(post.getPersonDTO() != null) {
			repository.save(obj);
			userService.addPostIn(obj);
			return obj;
		}else {
			throw new ObjectNotFoundException(post.getPersonDTO().getName());
		}
	}
	
	public Post fromDTO(PostDTO obj) {
		return new Post(obj.getId(),obj.getContent(),obj.getDate(),obj.getPersonDTO());
	}
}
