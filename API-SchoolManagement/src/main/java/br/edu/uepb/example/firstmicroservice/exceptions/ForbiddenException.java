package br.edu.uepb.example.firstmicroservice.exceptions;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ForbiddenException(String msg) {
		super(msg);
	}
}
