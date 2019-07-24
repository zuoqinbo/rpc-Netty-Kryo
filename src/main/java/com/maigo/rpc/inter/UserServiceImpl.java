package com.maigo.rpc.inter;

import com.maigo.rpc.utils.InfoPrinter;

import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService
{
	public String methodWithoutArg() 
	{
		return "this is return from methodWithoutArg()";
	}

	public String methodWithArgs(String arg1, int arg2) 
	{
		return arg1 + " = " + arg2;
	}

	public CustomObject methodWithCustomObject(
			CustomObject customObject) 
	{
		CustomObject object = new CustomObject(customObject.getString() + " after", 
				customObject.getI() + 47);
		return object;
	}

	public List<String> methodReturnList(String arg1, String arg2) 
	{
		return Arrays.asList(arg1, arg2);
	}

	public void methodThrowException()
	{
		throw new CustomException();
	}

	public void methodTimeOut() 
	{
		try 
		{
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	public void methodReturnVoid() 
	{
		return;
	}

	public String methodDelayOneSecond() 
	{
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		return "I have sleep 1000ms already.";
	}

	public int methodForMultiThread(int threadId) 
	{
		InfoPrinter.println("线程执行ID:"+threadId);
		return threadId;
	}

	public String methodForPerformance() 
	{
		return "Maigo";
	}
}
