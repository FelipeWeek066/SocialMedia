package com.Graimy.SocialMedia.domains.DTO;

import java.io.Serializable;

import com.Graimy.SocialMedia.enums.Vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	Vote vote;
	PersonDTO Author;
}
