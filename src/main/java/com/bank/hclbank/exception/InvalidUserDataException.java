package com.bank.hclbank.exception;

public class InvalidUserDataException extends Exception{

	public InvalidUserDataException() {
		super();
	}

	public InvalidUserDataException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InvalidUserDataException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidUserDataException(String arg0) {
		super(arg0);
	}

	public InvalidUserDataException(Throwable arg0) {
		super(arg0);
	}

	
}
