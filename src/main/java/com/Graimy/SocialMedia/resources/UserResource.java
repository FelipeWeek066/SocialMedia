package com.Graimy.SocialMedia.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Graimy.SocialMedia.domains.Post;
import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.domains.DTO.UserDTO;
import com.Graimy.SocialMedia.services.UserService;


@RestController
public class UserResource {
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/{name}/Posts")
	public ResponseEntity<List<Post>> findUserPosts(@PathVariable String name) {
		User obj = service.findByName(name);
		System.out.println(obj.getPosts());
		return ResponseEntity.ok().body(obj.getPosts());
	}
	
	@GetMapping(value = "/Users")
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> listDTO = service.findAll().stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	/*@GetMapping(value = "/{userId}")
	public ResponseEntity<UserDTO> findById(@PathVariable String userId) {
		UserDTO obj = new UserDTO(service.findById(userId));
		return ResponseEntity.ok().body(obj);
	}*/
	
	@GetMapping(value = "/{name}")
	public ResponseEntity<UserDTO> findByName(@PathVariable String name) {
		UserDTO obj = new UserDTO(service.findByName(name));
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/Users/{name}")
	public ResponseEntity<List<UserDTO>> findUsers(@PathVariable String name) {
		List<UserDTO> obj = service.searchName(name).stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(obj);
	}

}
