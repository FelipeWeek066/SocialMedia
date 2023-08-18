package com.Graimy.SocialMedia.domains.DTO;

import java.time.Instant;

import com.Graimy.SocialMedia.domains.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	private String id;
	private String content;
	private Instant date;
	private PersonDTO personDTO;
	
	public PostDTO(Post post) {
		setId(post.getId());
		setContent(post.getContent());
		setDate(post.getDate());
		setPersonDTO(post.getPersonDTO());
	}
}
