package br.edu.uepb.example.firstmicroservice.exceptions;

public class SameStudentAtProjectException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SameStudentAtProjectException(String msg) {
        super();
    }
}
