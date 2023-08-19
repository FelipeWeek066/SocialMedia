package com.Graimy.SocialMedia.domains;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.Graimy.SocialMedia.domains.DTO.CommentDTO;
import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.domains.DTO.VoteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Document
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String content;
	private PersonDTO author;
	private Instant date;
	private String linkedPostId;
	private Optional<String> linkedCommentId;
	private List<VoteDTO> votes = new ArrayList<>();
	
	public Comment(CommentDTO obj) 
	{
		setId(obj.getId());
		setContent(obj.getContent());
		setAuthor(obj.getAuthor());
		setDate(obj.getDate());
		setLinkedPostId(obj.getLinkedPostId());
		setLinkedCommentId(obj.getLinkedCommentId());
		setVotes(obj.getVotes());
	}
	
	
}
