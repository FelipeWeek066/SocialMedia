package com.Graimy.SocialMedia.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.Graimy.SocialMedia.domains.User;
import com.Graimy.SocialMedia.repository.UserRepository;

@Controller
public class TestConfig implements CommandLineRunner{
	@Autowired
	private UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
	
		User u1 = new User(null, "Felipe", "felipe@gmail.com", "pass123");
		User u2 = new User(null, "maria", "maria@gmail.com", "maria123");
		User u3 = new User(null, "Marcelin do grau", "Marcelin@Gmail.com", "marelo123");
		repository.saveAll(Arrays.asList(u1, u2, u3));
	}
}
