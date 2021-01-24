package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import netty.filter.RequestFilter;
import netty.router.DefaultRouter;
import netty.router.Router;

import java.util.List;
import java.util.Map;

public class HttpHandler extends ChannelInboundHandlerAdapter implements Interceptor {


    RequestFilter requestFilter;

    /**
     * key：服务名称
     * value：服务IP
     */
    Map<String, List<String>> serverMap;

    Router router;

    HttpOutbondHandler handler;

    public HttpHandler(RequestFilter requestFilter, Map<String, List<String>> serverMap) {
        this.requestFilter = requestFilter;
        this.serverMap = serverMap;
        handler = new HttpOutbondHandler();
        router = new DefaultRouter(serverMap);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            //前置处理
            preProcess(fullRequest, requestFilter);
            String serverAddress = router.route(uri);
            System.out.println("请求地址 ["+uri+"] , 路由地址 ["+serverAddress+"]" );
            handler.handler(fullRequest , ctx ,serverAddress);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
