package com.Graimy.SocialMedia.configs;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.Graimy.SocialMedia.domains.Comment;
import com.Graimy.SocialMedia.domains.Post;
import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.domains.DTO.CommentDTO;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.domains.DTO.PostDTO;
import com.Graimy.SocialMedia.repository.PostRepository;
import com.Graimy.SocialMedia.repository.UserRepository;
import com.Graimy.SocialMedia.services.CommentService;
import com.Graimy.SocialMedia.services.PostService;
import com.Graimy.SocialMedia.services.UserService;

@Controller
public class TestConfig implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User("Felipe", "felipe@gmail.com", "pass12345");
		User u2 = new User("maria", "maria@gmail.com", "maria12345");
		User u3 = new User("Marcelin do grau", "Marcelin@Gmail.com", "marelo12345");
		
		userService.insert(u1);
		userService.insert(u2);
		userService.insert(u3);
		
		Post p1 = new Post("Estou indo Viajar!!", new PersonDTO(u1), Instant.now());
		Post p2 = new Post("Alguem sabe uma receita de bolo", new PersonDTO(u2),Instant.now());
		Post p3 = new Post("alguem sabe um site torrent bacana??", new PersonDTO(u3), Instant.now());
		
		postService.insert(p1);
		postService.insert(p2);
		postService.insert(p3);
		
		System.out.println(p1.getId());
		Comment c1 = new Comment(null, "Parabens mano, boa viagem", new PersonDTO(u3), Instant.now(), p1.getId(), null, null);
		Comment c2 = new Comment(null, "pega 3 kilo de farinha 4 kilo de ovo mistura tudo e dai você joga fora", new PersonDTO(u3), Instant.now(), p2.getId(), null, null);
		Comment c3 = new Comment(null, "pirataria é crime man", new PersonDTO(u1), Instant.now(), p3.getId(), null, null);
		
		commentService.insert(c1);
		commentService.insert(c2);
		commentService.insert(c3);
		
	}
}
