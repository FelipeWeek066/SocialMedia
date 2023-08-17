package com.Graimy.SocialMedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Graimy.SocialMedia.domains.User;

public interface UserRepository extends MongoRepository<User, String>{

}
