package com.other.soa.server;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static javafx.scene.input.KeyCode.L;

/**
 * Created by pengyunlong on 2018/6/2.
 */

public class Server {
    private ChannelFactory factory;
    public static ChannelGroup channelGroup = new DefaultChannelGroup();
    private void start() {
        //NioServerSocketChannelFactory 用于创建NOIO的服务端
        //ServerSocketChannel 本身包含两种线程，boss线程和worker线程
        //每个ServerSocketChannel都会拥有自己的boss线程
        //当一个连接被服务端接受（accpeted）
        //boss线程就会将接收到的Channel传递给一个worker线程处理
        //而worker线程以非阻塞的方式为一个或多个Channel提供非阻塞读写
        factory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),//boss线程池
                Executors.newCachedThreadPool(),//worker线程池
                8);//worker线程数
        //ServerBootstrap用于帮助服务器启动
        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        //没有child.前缀，则该选项是为ServerSocketChannel设置
        bootstrap.setOption("reuseAddress",true);
        //有child.前缀，则该选项是为Channel设置
        bootstrap.setOption("child.tcpNoDelay",true);
        bootstrap.setOption("child.keepAlive",true);
        //对每一个连接（channel），server都会调用
        //channelPipelineFactory为该连接创建一个ChannelPipeline
        ServerChannelPiplineFactory channelPiplineFactory = new ServerChannelPiplineFactory();
        bootstrap.setPipelineFactory(channelPiplineFactory);

        //这里绑定服务监听的IP和端口
        Channel channel = bootstrap.bind(new InetSocketAddress("127.0.0.1", 8080));
        Server.channelGroup.add(channel);
        System.out.println("Server is started...");
    }
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        server.start();
        Thread.sleep(60*1000);
    }


}
