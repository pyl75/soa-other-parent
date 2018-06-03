package com.other.soa.client;

import org.jboss.netty.channel.*;

/**
 * Created by pengyunlong on 2018/6/2.
 */
public class ClientLogicalHandler extends SimpleChannelHandler {
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("#####channelConnected");
        Channel channel = e.getChannel();
        String msg = "Hi,Server.by agan";
        channel.write(msg);
    }
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("#####messageRecived");
        String msg = (String) e.getMessage();
        System.out.println("The message gotten from server is : "+msg);
        ChannelFuture channelFuture = e.getChannel().close();
        channelFuture.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
        System.out.println("#####writeComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }

}
