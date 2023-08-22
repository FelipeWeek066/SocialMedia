package com.Graimy.SocialMedia.domains.DTO;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.Graimy.SocialMedia.domains.Post;
import com.Graimy.SocialMedia.enums.Vote;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String content;
	private Instant date;
	private PersonDTO AuthorDTO;
	private int commentQuantity;
	private int daoraQuantity;
	private int odieiQuantity;
	
	public PostDTO(Post post) {
		setId(post.getId());
		setContent(post.getContent());
		setDate(post.getDate());
		setAuthorDTO(post.getAuthorDTO());
		setCommentQuantity(post.getComments().size());
		setDaoraQuantity(post.getVotes().stream().map(x -> x.getVote() == Vote.daora).collect(Collectors.toList()).size());
		setOdieiQuantity(post.getVotes().stream().map(x -> x.getVote() == Vote.odiei).collect(Collectors.toList()).size());
	}
}
