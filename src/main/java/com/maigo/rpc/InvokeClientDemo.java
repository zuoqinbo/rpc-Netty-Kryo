package com.maigo.rpc;

import com.maigo.rpc.client.RpcClientAsyncProxy;
import com.maigo.rpc.client.RpcClientProxyBuilder;
import com.maigo.rpc.future.RpcFuture;
import com.maigo.rpc.inter.CustomException;
import com.maigo.rpc.inter.CustomObject;
import com.maigo.rpc.inter.UserService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * InvokClient
 *
 * @author ZuoQinbo
 * @date 2019/7/24
 */
public class InvokeClientDemo {

    public static void main(String[] args) {

        RpcClientAsyncProxy rpcClientAsyncProxy =  RpcClientProxyBuilder.create(UserService.class)
                .timeout(2000)
                .threads(4)
                .connect("127.0.0.1", 3721)
                .buildAsyncProxy();
        //异步调用
        RpcFuture rpcFuture = rpcClientAsyncProxy.call("methodWithoutArg");

//        try
//        {
//            rpcFuture.get();
//        }
//        catch (Throwable e)
//        {
//            if(e instanceof CustomException)
//                return;
//
//            fail(e + " was caught when testFutureError.");
//        }
//
//        fail("failed to catch JUnitTestCustomException.");
        //动态代理直接调用
        UserService userService = RpcClientProxyBuilder.create(UserService.class)
                .timeout(2000)
                .threads(4)
                .connect("127.0.0.1", 3721)
                .build();


        CustomObject beforeCustomObject = new CustomObject("before", 3);
        CustomObject afterCustomObject =
                userService.methodWithCustomObject(beforeCustomObject);
        assertEquals("before after", afterCustomObject.getString());

        assertEquals(50, afterCustomObject.getI());

        List<String> list = userService.methodReturnList("hello", "world");

        System.out.println(list.get(0));
    }
}