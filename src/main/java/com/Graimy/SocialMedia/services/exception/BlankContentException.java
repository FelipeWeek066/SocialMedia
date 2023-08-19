package com.Graimy.SocialMedia.services.exception;

public class BlankContentException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public BlankContentException(String msg) {
		super(msg);
	}
}
