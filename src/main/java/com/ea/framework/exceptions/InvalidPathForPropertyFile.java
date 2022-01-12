package com.ea.framework.exceptions;

@SuppressWarnings("serial")
public class InvalidPathForPropertyFile extends FrameworkException {

	public InvalidPathForPropertyFile(String message)
	{
		super(message);
	}
	
	public InvalidPathForPropertyFile(String message, Throwable cause)
	{
		super(message, cause);
	}

}
