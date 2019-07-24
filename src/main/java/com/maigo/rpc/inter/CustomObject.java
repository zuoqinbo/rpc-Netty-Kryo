package com.maigo.rpc.inter;

public class CustomObject
{
	private String string;
	private int i;

	public CustomObject(String string, int i)
	{
		super();
		this.string = string;
		this.i = i;
	}

	public String getString()
	{
		return string;
	}

	public void setString(String string)
	{
		this.string = string;
	}

	public int getI()
	{
		return i;
	}

	public void setI(int i)
	{
		this.i = i;
	}

	@Override
	public boolean equals(Object obj)
	{
		CustomObject object = null;
		if(obj instanceof CustomObject)
			object = (CustomObject)obj;
		else
			return false;

		return (this.string.equals(object.string)) && (this.i == object.i);
	}
}
