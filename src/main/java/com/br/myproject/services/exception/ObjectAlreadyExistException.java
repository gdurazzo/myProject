package com.br.myproject.services.exception;

public class ObjectAlreadyExistException  extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectAlreadyExistException(String msg) {
		super(msg);
	}

}
