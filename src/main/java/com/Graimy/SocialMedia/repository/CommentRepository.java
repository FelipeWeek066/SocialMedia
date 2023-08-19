package com.Graimy.SocialMedia.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Graimy.SocialMedia.domains.Comment;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;

public interface CommentRepository extends MongoRepository<Comment, String>{
	List<Comment> findByLinkedPostId(String linkedPostId);
	
	List<Comment> findByAuthor(PersonDTO author);
}
