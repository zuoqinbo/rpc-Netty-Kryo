package com.maigo.rpc.serializer;

import com.esotericsoftware.kryo.Kryo;


/**
 *  使用threadLocalKryo 来做线程序列化隔离
 *
 */
public class KryoHolder 
{
	private static ThreadLocal<Kryo> threadLocalKryo = new ThreadLocal<Kryo>()
	{
		protected Kryo initialValue() 
		{
			Kryo kryo = new KryoReflectionFactory();
			return kryo;
		}
	};
	
	public static Kryo get()
	{
		return threadLocalKryo.get();
	}
}
