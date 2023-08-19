package com.Graimy.SocialMedia.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.Graimy.SocialMedia.domains.Post;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;

public interface PostRepository extends MongoRepository<Post, String>{

	List<Post> findByAuthorDTO(PersonDTO obj);
	
	@Query("{ $and: [{date: {$gte: ?1}}, {date: {$lte: ?2}}, { $or: [{'content': { $regex: ?0, $options: 'i'} }, {'personDTO': { $regex: ?0, $options: 'i'} } ]}]}")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
