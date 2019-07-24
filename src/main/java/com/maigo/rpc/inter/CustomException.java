package com.maigo.rpc.inter;

public class CustomException extends RuntimeException
{
	private static final long serialVersionUID = 591530421634999576L;

	public CustomException()
	{
		super("CustomException");
	}
}
