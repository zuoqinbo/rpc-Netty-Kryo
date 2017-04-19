package com.maigo.rpc.netty;

import com.maigo.rpc.serializer.KryoSerializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;


/**
 * 采用 Kryo 序列化框架 解码
 *
 */
public class NettyKryoDecoder extends LengthFieldBasedFrameDecoder
{

    /**
     *
     * @param maxFrameLength 解码时，处理每个帧数据的最大长度
     * @param lengthFieldOffset 该帧数据中，存放该帧数据的长度的数据的起始位置
     * @param lengthFieldLength 记录该帧数据长度的字段本身的长度
     * @param lengthAdjustment 修改帧数据长度字段中定义的值，可以为负数
     * @param initialBytesToStrip 解析的时候需要跳过的字节数
     */
//    public LengthFieldBasedFrameDecoder(
//            int maxFrameLength,
//            int lengthFieldOffset, int lengthFieldLength) {
//        this(maxFrameLength, lengthFieldOffset, lengthFieldLength, 0, 0);
//    }
    public NettyKryoDecoder()
    {
        super(1048576, 0, 4, 0, 4);
    }
	
	@Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception 
    {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) 
            return null;
                
        return KryoSerializer.deserialize(frame);
    }

    @Override
    protected ByteBuf extractFrame(ChannelHandlerContext ctx, ByteBuf buffer, int index, int length) 
    {
        return buffer.slice(index, length);
    }
}
