package com.Graimy.SocialMedia.configs;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.Graimy.SocialMedia.domains.Post;
import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.repository.PostRepository;
import com.Graimy.SocialMedia.repository.UserRepository;

@Controller
public class TestConfig implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();

		
		User u1 = new User("Felipe", "felipe@gmail.com", "pass123");
		User u2 = new User("maria", "maria@gmail.com", "maria123");
		User u3 = new User("Marcelin do grau", "Marcelin@Gmail.com", "marelo123");
		
		userRepository.saveAll(Arrays.asList(u1,u2,u3));
		
		Post p1 = new Post("Estou indo Viajar!!", new PersonDTO(u1), Instant.now());
		Post p2 = new Post("Alguem sabe uma receita de bolo", new PersonDTO(u2),Instant.now());
		Post p3 = new Post("alguem sabe um site torrent bacana??", new PersonDTO(u3), Instant.now());
		Post p4 = new Post("videos safados videos libidinosos google pesquisar", new PersonDTO(u3), Instant.now());
		
		
		postRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
		u1.getPosts().add(p1);
		u2.getPosts().add(p2);
		u3.getPosts().addAll(Arrays.asList(p3, p4));
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
	}
}
