package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author lihongjian
 * @since 2021/1/17
 */
public class NettyServerAplication {

    static Map<String, List<String>> serverMap = new ConcurrentHashMap<>();

    /**
     * 假设有个两个服务注册到网关中
     */
    static {
        String apiServerAddress = "http://127.0.0.1:8890,http://127.0.0.1:8891,http://127.0.0.1:8892";
        String helloServerAddress = "http://127.0.0.1:8900,http://127.0.0.1:8901,http://127.0.0.1:8902";
        serverMap.put("api" , Arrays.stream(apiServerAddress.split(",")).collect(Collectors.toList()));
        serverMap.put("hello" , Arrays.stream(helloServerAddress.split(",")).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        int port = 8999;

        try {
            EventLoopGroup bossGroup = new NioEventLoopGroup(2);
            EventLoopGroup workGroup = new NioEventLoopGroup(8);

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR , true)
                    .option(ChannelOption.SO_RCVBUF , 32*1024)
                    .option(ChannelOption.SO_SNDBUF , 32*1024)
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.group(bossGroup , workGroup).
                    channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInitializer(serverMap));
            Channel channel = bootstrap.bind(port).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
