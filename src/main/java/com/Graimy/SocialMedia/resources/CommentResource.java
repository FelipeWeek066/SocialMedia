package com.Graimy.SocialMedia.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.domains.DTO.CommentDTO;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.services.CommentService;
import com.Graimy.SocialMedia.services.UserService;
import com.Graimy.SocialMedia.services.exception.ObjectNotFoundException;

@RestController
public class CommentResource {
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	
	@GetMapping(value = "/Users/{userName}/Comments")
	public ResponseEntity<List<CommentDTO>> userComments(@PathVariable String userName){
		User user = userService.findByName(userName);
		List<CommentDTO> list = commentService.findByUserId(new PersonDTO(user)).stream().map(x -> new CommentDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/Posts/{postId}/Comments")
	public ResponseEntity<List<CommentDTO>> postComments(@PathVariable String postId){
		List<CommentDTO> list = commentService.findByPostId(postId).stream().map(x -> new CommentDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping(value = "/Posts/{postId}/{userName}")
	public ResponseEntity<Void> addComment(@PathVariable String userName, @PathVariable String postId, @RequestBody CommentDTO comment){
		User user = userService.findByName(userName);
		comment.setAuthor(new PersonDTO(user));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postId}/{userName}").buildAndExpand(comment.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/Users/{userId}/{commentId}")
	public ResponseEntity<Void> removeComment(@PathVariable String userId, @PathVariable String commentId){
		User user = userService.findById(userId);
		if(commentService.findById(commentId).getAuthor().getName() == user.getName()){
		commentService.delete(userId, commentId);
		return ResponseEntity.ok().build();
		}else {
			throw new ObjectNotFoundException("diferent author");
		}
	}
}
