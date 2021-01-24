package netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import netty.filter.DefaultRequestHeaderFilter;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author lihongjian
 * @since 2021/1/20
 */
public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    Map<String, List<String>> serverMap;

    public HttpInitializer(Map<String, List<String>> serverMap) {
        this.serverMap = serverMap;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new HttpHandler(new DefaultRequestHeaderFilter(), serverMap));
    }
}
