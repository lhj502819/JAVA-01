package io.kimmking.rpcfx.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description：实现客户端Channel断开连接、异常时的处理。
 *
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/2/19
 */
@Slf4j
@Component
@ChannelHandler.Sharable //标记当前这个ChannelHandler可以被多个Channel使用
public class NettyServerInbondHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private HttpOutbondHandler handler;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            //前置处理
            handler.handler(fullRequest, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 在处理Channel事件发生异常时，调用{@link Channel#close()}断开和客户端的连接
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("[exceptionCaught]连接[{}]发生异常", ctx.channel().id(), cause);
        ctx.channel().close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
