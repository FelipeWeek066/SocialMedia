package com.Graimy.SocialMedia.domains;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.Graimy.SocialMedia.domains.DTO.PersonDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private PersonDTO personDTO;
	
	public Post(String content, PersonDTO personDTO, Instant date) {
		this.content = content;
		this.personDTO = personDTO;
		this.date = date;
	}
}
