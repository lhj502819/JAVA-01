package io.kimmking.rpcfx.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author li.hongjian
 * @email lhj502819@163.com
 * @Date 2021/3/19
 */
@Slf4j
@Component
public class NettyServer {

    @Autowired
    private NettyServerHandlerInitializer nettyServerHandlerInitializer;

    /**
     * Netty采用的是多Reactor多线程的模型，服务端可以接受更多客户端的数据读写能力。原因是
     * 1、创建专门用于接受客户端连接的bossGroup线程组，避免因为已连接的客户端的数据读写频繁，影响新的客户端的连接
     * 2、创建专门用于接收客户端读写的workerGroup线程组，多个线程进行客户端的数据读写，可以支持更多客户端
     */

    /**
     * boss 线程组，用于服务端接受客户端的连接
     */
    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    /**
     * worker 线程组，用于服务端接受客户端的数据读写
     */
    private EventLoopGroup workerGroup = new NioEventLoopGroup(8);

    private Integer port = 8088;

    @PostConstruct
    public void start() throws InterruptedException {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.option(ChannelOption.SO_REUSEADDR, true);
            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            serverBootstrap.childHandler(nettyServerHandlerInitializer);
            serverBootstrap.bind(port).sync();
            log.info("netty启动成功 {}" , port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void shutdown(){
        //优雅关闭两个EventLoopGroup
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
