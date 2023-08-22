package com.Graimy.SocialMedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Graimy.SocialMedia.domains.Comment;
import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.domains.DTO.VoteDTO;
import com.Graimy.SocialMedia.repository.CommentRepository;
import com.Graimy.SocialMedia.services.exception.BlankContentException;
import com.Graimy.SocialMedia.services.exception.ObjectNotFoundException;

@Service
public class CommentService {
	@Autowired
	private CommentRepository repository;
	@Autowired
	private PostService postService;
	@Autowired 
	UserService userService;
	
	public List<Comment> findByPostId(String postId) {
		postService.findById(postId);
		return repository.findByLinkedPostId(postId);
	}
	
	public Comment findById(String CommentId) {
		Optional<Comment> obj = repository.findById(CommentId);
		return obj.orElseThrow(() -> new ObjectNotFoundException(CommentId));
	}
	
	public List<Comment> findByUserId(PersonDTO userDTO) {
		userService.findByName(userDTO.getName());
		return repository.findByAuthor(userDTO);
	}
	
	public void addVote(String CommentId, VoteDTO vote) {
		Comment com = findById(CommentId);
		com.getVotes().removeIf(x -> x.getAuthor().getName().equals(vote.getAuthor().getName())); 
		com.getVotes().add(vote);
		repository.save(com);
	}
	
	public void remVote(String CommentId, PersonDTO author) {
		Comment com = findById(CommentId);
		com.getVotes().removeIf(x -> x.getAuthor().getName().equals(author.getName())); 
		repository.save(com);
	}
	
	public void insert(Comment comment) {
		userService.findByName(comment.getAuthor().getName());
		postService.findById(comment.getLinkedPostId());
		if(!comment.getContent().isBlank()) {
			Comment obj = comment;
			repository.save(obj);
		}else {
			throw new BlankContentException("Content cannot be blank");
		}
	}
	
	public void delete(String userId,String id) {
		User user = userService.findById(userId);
		if(findById(id).getAuthor().getName() == user.getName()){
			postService.findById(findById(id).getLinkedPostId());
			repository.delete(findById(id));
		}else {
			throw new ObjectNotFoundException("diferent author");
		}
	}
}
