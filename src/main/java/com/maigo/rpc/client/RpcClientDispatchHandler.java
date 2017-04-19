package com.maigo.rpc.client;

import com.maigo.rpc.context.RpcResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * 在客户端的业务Handler继承的是SimpleChannelInboundHandler，而在服务器端继承的是ChannelInboundHandlerAdapter
 * 最主要的区别就是SimpleChannelInboundHandler在接收到数据后会自动release掉数据占用的Bytebuffer资源(自动调用Bytebuffer.release())。
 * 而为何服务器端不能用呢，因为我们想让服务器把客户端请求的数据发送回去，
 * 而服务器端有可能在channelRead方法返回前还没有写完数据，因此不能让它自动release
 */
public class RpcClientDispatchHandler extends ChannelInboundHandlerAdapter
{
	private RpcClientResponseHandler rpcClientResponseHandler;
	private RpcClientChannelInactiveListener rpcClientChannelInactiveListener = null;
	
	public RpcClientDispatchHandler(
			RpcClientResponseHandler rpcClientResponseHandler, 
			RpcClientChannelInactiveListener rpcClientChannelInactiveListener) 
	{
		this.rpcClientResponseHandler = rpcClientResponseHandler;
		this.rpcClientChannelInactiveListener = rpcClientChannelInactiveListener;
	}


	/**
	 * 通道读取数据
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception 
	{		
		RpcResponse rpcResponse = (RpcResponse)msg;
		rpcClientResponseHandler.addResponse(rpcResponse);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception 
	{
		
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception 
	{
		if(rpcClientChannelInactiveListener != null)
			rpcClientChannelInactiveListener.onInactive();
	}	
}
