package com.Graimy.SocialMedia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.Graimy.SocialMedia.domains.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	Optional<User> findByName(String name);
	
	@Query("{'name': { $regex: ?0, $options: 'i'} }")
	List<User> searchName(String name);
}
