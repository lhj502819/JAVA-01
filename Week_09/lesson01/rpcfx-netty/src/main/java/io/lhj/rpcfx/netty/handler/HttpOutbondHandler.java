package io.lhj.rpcfx.netty.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.lhj.rpcfx.api.RpcfxRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 请求处理器
 *
 * @author lihongjian
 * @since 2021/1/24
 */
@Slf4j
@Component
public class HttpOutbondHandler{

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    public void handler(FullHttpRequest fullRequest, ChannelHandlerContext ctx) throws Exception{
        if(fullRequest.method() == HttpMethod.POST){
            post(fullRequest , ctx);
        }
    }

    private void post(FullHttpRequest fullRequest, ChannelHandlerContext ctx) throws IOException {
        ByteBuf byteBuf = fullRequest.content();
        int length = byteBuf.readableBytes();
        byte[] array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), array);
        String content = new String(array, "UTF-8");
        RpcfxRequest rpcfxRequest = JSONObject.parseObject(content, RpcfxRequest.class);
        String reqJson = JSON.toJSONString(rpcfxRequest);
        System.out.println("req json: " + reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(rpcfxRequest.getUrl())
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        Response response = client.newCall(request).execute();
        try {
            handleResponse(fullRequest , ctx , response);
        } catch (Exception e) {
            log.error("[HttpOutbondHandler#post][netty发送请求异常]" , e);
        }
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final Response httpResponse) throws Exception {
        FullHttpResponse response = null;
        ResponseBody body = httpResponse.body();
        try {
            String string = body.string();
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(string.getBytes("UTF-8")));

            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", string.getBytes().length);


        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
