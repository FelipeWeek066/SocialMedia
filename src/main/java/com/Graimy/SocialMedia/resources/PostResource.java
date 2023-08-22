package com.Graimy.SocialMedia.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Graimy.SocialMedia.domains.Post;
import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.domains.DTO.PostDTO;
import com.Graimy.SocialMedia.resources.url.URL;
import com.Graimy.SocialMedia.services.PostService;
import com.Graimy.SocialMedia.services.UserService;


@RestController
@CrossOrigin("*")
public class PostResource {
	@Autowired
	private PostService service;
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/Posts")
	public ResponseEntity<List<Post>> findPosts(){	
		List<Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{name}/Posts")
	public ResponseEntity<List<PostDTO>> findUserPosts(@PathVariable String name) {
		User obj = userService.findByName(name);
		return ResponseEntity.ok().body(service.findByUser(new PersonDTO(obj)).stream().map(x -> new PostDTO(x)).collect(Collectors.toList()));
	}
	
	@PostMapping(value = "/{userName}")
	public ResponseEntity<Post> insert(@PathVariable String userName, @RequestBody PostDTO post){	
		post.setAuthorDTO(new PersonDTO(userService.findByName(userName)));
		service.insert(new Post(post));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userName}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@GetMapping(value = "/Posts/fullsearch")
	public ResponseEntity<List<Post>> findPosts(@RequestParam(value = "text", defaultValue="") String text,
													@RequestParam(value = "minDate", defaultValue="") String minDate,
													@RequestParam(value = "maxDate", defaultValue="") String maxDate) {
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		text = URL.decodeparam(text);
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
}
