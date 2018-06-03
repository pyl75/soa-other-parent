package com.other.soa.client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by pengyunlong on 2018/6/2.
 */
public class Client {
    public static void main(String[] args) {
        //同服务端相同，只是这里使用的是NioClientSocketChannelFactory
        final ChannelFactory factory = new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool(),
                8
        );
        //ClientBootStrap 用于帮助客户端启动
        ClientBootstrap bootstrap = new ClientBootstrap(factory);
        //由于客户端不包含ServerSocketChannel，所以参数名不能带child.前缀
        bootstrap.setOption("tcpNoDelay",true);
        //bootstrap.setOption("keepAlive",true);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast("handler",new ClientLogicalHandler());
                return pipeline;
            }
        });
        //这里连接服务端绑定的IP和端口
        bootstrap.connect(new InetSocketAddress("127.0.0.1",8080));
        System.out.println("Client is started...");
    }
}
