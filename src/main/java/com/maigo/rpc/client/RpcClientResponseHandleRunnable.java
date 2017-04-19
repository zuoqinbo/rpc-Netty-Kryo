package com.maigo.rpc.client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

import com.maigo.rpc.context.RpcResponse;
import com.maigo.rpc.future.RpcFuture;

public class RpcClientResponseHandleRunnable implements Runnable
{
	private ConcurrentMap<Integer, RpcFuture> invokeIdRpcFutureMap;

	/**
	 * 多线程从响应结果的阻塞队列中去取数据
	 *
	 */
	private BlockingQueue<RpcResponse> responseQueue;
	
	public RpcClientResponseHandleRunnable(
			ConcurrentMap<Integer, RpcFuture> invokeIdRpcFutureMap,
			BlockingQueue<RpcResponse> responseQueue) 
	{
		this.invokeIdRpcFutureMap = invokeIdRpcFutureMap;
		this.responseQueue = responseQueue;
	}

	@Override
	public void run() 
	{
		while(true)
		{
			try 
			{
				RpcResponse rpcResponse = responseQueue.take();
				//取出一个响应结果
				int id = rpcResponse.getId();
				RpcFuture rpcFuture = invokeIdRpcFutureMap.remove(id);
				
				if(rpcResponse.isInvokeSuccess())				
					rpcFuture.setResult(rpcResponse.getResult());	
				else
					rpcFuture.setThrowable(rpcResponse.getThrowable());				
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
