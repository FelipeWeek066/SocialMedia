package com.Graimy.SocialMedia.domains;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.Graimy.SocialMedia.domains.DTO.PersonDTO;
import com.Graimy.SocialMedia.domains.DTO.PostDTO;
import com.Graimy.SocialMedia.domains.DTO.VoteDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String content;
	private Instant date;
	private PersonDTO authorDTO;
	@Setter(value = AccessLevel.NONE)
	@DBRef(lazy = true)
	private List<Comment> comments = new ArrayList<>();
	@Setter(value = AccessLevel.NONE)
	private List<VoteDTO> votes = new ArrayList<>();
	public Post(PostDTO obj) {
		setId(obj.getId());
		setContent(obj.getContent());
		setAuthorDTO(obj.getAuthorDTO());
		setDate(obj.getDate());
	}
	
	public Post(String content, PersonDTO authorDTO, Instant date) {
		this.content = content;
		this.authorDTO = authorDTO;
		this.date = date;
	}
	
}
