package com.maigo.rpc.test;

import static org.junit.Assert.*;

import com.maigo.rpc.inter.UserService;
import com.maigo.rpc.inter.UserServiceImpl;
import org.junit.Test;

import com.maigo.rpc.server.RpcServer;
import com.maigo.rpc.server.RpcServerBuilder;

public class JUnitServerTest 
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
		new JUnitServerTest().testServerStart();
	}
}
