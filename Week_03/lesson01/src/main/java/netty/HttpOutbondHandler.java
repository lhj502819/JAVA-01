package netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import netty.filter.DefaultReponseHeaderFilter;
import netty.filter.ResponseFilter;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 请求处理器
 *
 * @author lihongjian
 * @since 2021/1/24
 */
public class HttpOutbondHandler implements Interceptor{
    ResponseFilter responseFilter;

    /**
     * 可添加自定义过滤器覆盖
     */
    public HttpOutbondHandler() {
        //if(customFilter == null){
        this.responseFilter = new DefaultReponseHeaderFilter();
        //}else{responseFilter = customFilter}
    }

    public void handler(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String serverAddress) throws Exception{
        Response response = null;
        if(fullRequest.method() == HttpMethod.GET){
            response = doGet(fullRequest , ctx , serverAddress + fullRequest.uri());
        }
        if(fullRequest.method() == HttpMethod.POST){
            response = doPost(fullRequest , ctx , serverAddress);
        }
        responseConvert(response, fullRequest , ctx);
    }

    public Response doGet(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String serverAddress) throws Exception{
        ByteBuf content = fullRequest.content();
        if (content.readableBytes() > 0) {
            serverAddress += new String(content.array());
        }
        return OkhttpHttpUtils.sendGet(serverAddress);
    }

    public Response doPost(FullHttpRequest fullRequest, ChannelHandlerContext ctx , String serverAddress) throws Exception{
        ByteBuf byteBuf = fullRequest.content();
        int length = byteBuf.readableBytes();
        byte[] array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), array);
        String s = new String(array, "UTF-8");
        Map map = JSONObject.parseObject(s, Map.class);
        return OkhttpHttpUtils.sendPost(serverAddress + fullRequest.uri() , map);
    }

    public void responseConvert(Response response, FullHttpRequest fullRequest, ChannelHandlerContext ctx){
        FullHttpResponse fullHttpResponse = null;
        ResponseBody body = response.body();
        try {
            String string = body.string();
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(string.getBytes("UTF-8")));
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length", string.getBytes().length);
            postProcess(fullHttpResponse , responseFilter);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("处理出错:"+e.getMessage());
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        }finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullHttpResponse);
                }
            }
            ctx.flush();
            //ctx.close();
        }
    }


    @Override
    public void postProcess(FullHttpResponse fullHttpResponse, ResponseFilter responseFilter) {
        responseFilter.filter(fullHttpResponse);
    }
}
