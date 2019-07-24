package com.maigo.rpc;

import com.maigo.rpc.inter.UserService;
import com.maigo.rpc.inter.UserServiceImpl;
import com.maigo.rpc.server.RpcServer;
import com.maigo.rpc.server.RpcServerBuilder;
import org.junit.Test;

public class InvokeServerDemo
{
	@Test
	public void testServerStart() 
	{
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		RpcServer rpcServer = RpcServerBuilder.create()
				  .serviceInterface(UserService.class)
				  .serviceProvider(userServiceImpl)
				  .threads(4)
				  .bind(3721)
				  .build();
		rpcServer.start();
	}
	
	public static void main(String[] args) 
	{
		new InvokeServerDemo().testServerStart();
	}
}
