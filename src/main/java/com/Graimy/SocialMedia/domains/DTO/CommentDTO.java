package com.Graimy.SocialMedia.domains.DTO;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.Graimy.SocialMedia.domains.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String content;
	private String linkedPostId;
	private Optional<String> linkedCommentId;
	private Instant date;
	private PersonDTO author;
	private List<VoteDTO> votes;
	
	public CommentDTO(Comment obj) {
		setId(obj.getId());
		setContent(obj.getContent());
		setLinkedPostId(obj.getLinkedPostId());
		setLinkedCommentId(obj.getLinkedCommentId());
		setDate(obj.getDate());
		setAuthor(obj.getAuthor());
		setVotes(obj.getVotes());
		
	}
}
