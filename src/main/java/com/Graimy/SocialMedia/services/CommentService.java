package com.Graimy.SocialMedia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Graimy.SocialMedia.domains.Comment;
import com.Graimy.SocialMedia.domains.DTO.CommentDTO;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.repository.CommentRepository;
import com.Graimy.SocialMedia.services.exception.BlankContentException;

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
	
	public List<Comment> findByUserId(PersonDTO userDTO) {
		userService.findByName(userDTO.getName());
		return repository.findByAuthor(userDTO);
	}
	
	public void insert(Comment comment) {
		userService.findByName(comment.getAuthor().getName());
		postService.findById(comment.getLinkedPostId());
		if(!comment.getContent().isBlank()) {
			Comment obj = comment;
			repository.save(obj);
			postService.addCommentIn(obj);
			userService.addCommentIn(obj);
		}else {
			throw new BlankContentException("Content cannot be blank");
		}
	}
}
