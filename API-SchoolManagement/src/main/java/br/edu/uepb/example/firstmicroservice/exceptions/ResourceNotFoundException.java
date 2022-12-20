package br.edu.uepb.example.firstmicroservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
		super("Entity not found: " + msg);
	}
}
