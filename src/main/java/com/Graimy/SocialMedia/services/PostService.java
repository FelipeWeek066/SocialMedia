package com.Graimy.SocialMedia.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Graimy.SocialMedia.domains.Post;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.domains.DTO.VoteDTO;
import com.Graimy.SocialMedia.repository.PostRepository;
import com.Graimy.SocialMedia.services.exception.BlankContentException;
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
		return repository.findByAuthorDTO(dto);
	}
	
	public void addVote(String postId, VoteDTO vote) {
		Post post = findById(postId);
		post.getVotes().removeIf(x -> x.getAuthor().getName().equals(vote.getAuthor().getName())); 
		post.getVotes().add(vote);
		repository.save(post);
	}
	
	public void remVote(String postId, PersonDTO author) {
		Post post = findById(postId);
		post.getVotes().removeIf(x -> x.getAuthor() == author);
		repository.save(post);
	}
	
	public Post insert(Post post) {
		post.setDate(Instant.now());
		if(!post.getContent().isBlank()) {
			userService.findByName(post.getAuthorDTO().getName());
			return repository.save(post);
		}else {
			throw new BlankContentException("the content of post is blank");
		}
	}
}
